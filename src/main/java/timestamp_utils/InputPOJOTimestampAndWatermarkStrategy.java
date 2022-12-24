package timestamp_utils;

import model.InputPOJO;
import org.apache.flink.api.common.eventtime.*;

public class InputPOJOTimestampAndWatermarkStrategy implements WatermarkStrategy<InputPOJO> {

    @Override
    public TimestampAssigner<InputPOJO> createTimestampAssigner(TimestampAssignerSupplier.Context context) {
        return (element, recordTimestamp) -> element.getTimestamp();
    }

    @Override
    public WatermarkGenerator<InputPOJO> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
        return new WatermarkGenerator<InputPOJO>() {
            @Override
            public void onEvent(InputPOJO event, long eventTimestamp, WatermarkOutput output) {
                output.emitWatermark(new Watermark(event.getTimestamp()));
            }

            @Override
            public void onPeriodicEmit(WatermarkOutput output) {
                output.emitWatermark(new Watermark(System.currentTimeMillis()));
            }
        };
    }
}