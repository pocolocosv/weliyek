/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.basic.string;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.Disaggregator;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinitionCore;
import weliyek.amat.basic.aggregator.WkSzSubcomponentCore;
import weliyek.ketza.util.array.WkSzPrimitiveArrayDefinition;
import weliyek.ketza.util.array.PrimitiveArrayReading;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;
import weliyek.ketza.util.array.PrimitiveArrayWriting;

public abstract class WkSzStringFromPrimitiveDefinitionCore<
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,?>,
                        XR extends DeserializingResult<String>,
                        XO extends WkSzStringFromPrimitiveReader<XS,? extends DeserializingRuntime<XB>,XR,XD,ST,SXD,SXO>,
                        XD extends WkSzStringFromPrimitiveDefinition<XO,?,? extends SXD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,?>,
                        YR extends SerializingResult,
                        YO extends WkSzStringFromPrimitiveWriter<YS,? extends SerializingRuntime<YB>,YR,YD,ST,SYD,SYO>,
                        YD extends WkSzStringFromPrimitiveDefinition<?,YO,? extends SYD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        ST extends PrimitiveArrayWrapper<?,?>,
                        SXS extends OperationSettings,
                        SXO extends PrimitiveArrayReading<ST,SXS,?,?,SXD>,
                        SXD extends WkSzPrimitiveArrayDefinition<ST,SXO>,
                        SYS extends OperationSettings,
                        SYO extends PrimitiveArrayWriting<ST,SYS,?,?,SYD>,
                        SYD extends WkSzPrimitiveArrayDefinition<ST,?>,
                        SD extends WkSzPrimitiveArrayDefinition<ST, SXO>,
                        D extends WkSzStringFromPrimitiveDefinition<XO,YO,SD>,
                        DC extends WkSzStringFromPrimitiveDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      ST,SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSzAggregatorDefinitionCore<String, XS, XB, XBC, XQC, XR, XD, XO, AXB, YS, YB, YBC, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSzStringFromPrimitiveDefinition<XO, YO, SD>
{

  final WkSzSubcomponentCore<ST,SXS,SXD,SXO,String,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
                primitiveArraySubcomponent;

  final Function<XO, String> primitiveArrayDeserializingStringAggregator;

  protected WkSzStringFromPrimitiveDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    PacketInputFieldReadingFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    PacketOutputFieldWritingFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String primitiveArrayLabel,
    Optional<Predicate<? super XO>> primitiveArrayDeserializationEnablingTest,
    OperationSubsegmentSettingsFactory<XO,SXS> primitiveArrayDeserializingSettingsFactory,
    Function<XO, String> primitiveArrayDeserializingStringAggregator,
    Optional<Predicate<? super YO>> primitiveArraySerializationEnablingTest,
    OperationSubsegmentSettingsFactory<YO,SYS> primitiveArraySerializingSettingsFactory,
    Disaggregator<ST, SYD, String, YO> primitiveArraySerializingDisaggregator,
    boolean primitiveArrayDeserializedRequired,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> primitiveArrayDefinitionFactory,
    D definitionBody) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          definitionBody,
          String.class);
    this.primitiveArraySubcomponent = this.<ST,SXS,SXD,SXO,SYS,SYD,SYO,SD>addSubcomponent(
                                               primitiveArrayLabel,
                                               primitiveArrayDeserializationEnablingTest,
                                               singleOperation(),
                                               primitiveArrayDeserializingSettingsFactory,
                                               primitiveArraySerializationEnablingTest,
                                               singleOperation(),
                                               primitiveArraySerializingSettingsFactory,
                                               primitiveArraySerializingDisaggregator,
                                               primitiveArrayDeserializedRequired,
                                               primitiveArrayDefinitionFactory);
    this.primitiveArrayDeserializingStringAggregator = Objects.requireNonNull(primitiveArrayDeserializingStringAggregator);
  }

  public abstract static class PrimitiveArrayDisaggregatorFromString<
                          YO extends WkSzStringFromPrimitiveWriter<?,?,?,?,ST,SD,?>,
                          ST extends PrimitiveArrayWrapper<?,?>,
                          SD extends WkSzPrimitiveArrayDefinition<ST,?>,
                          WA>
      implements Disaggregator<ST, SD, String, YO>
  {

    @Override
    public ST disaggregate(
      WkSzPacketWriterField<ST,SD,?> primitiveArraySerializingField,
      YO stringSerializingOperation,
      int primitiveArraySerializingOperationIndex) {
      WA array = getPrimitiveArrayFromString(stringSerializingOperation);
      int originalSize = getPrimitiveArrayLength(array);
      Optional<Integer> overridingSizeRequest = requestedPrimitiveArrayLength(stringSerializingOperation);
      if (overridingSizeRequest.isPresent() && (overridingSizeRequest.get().intValue() != originalSize)) {
        int overridingSize = overridingSizeRequest.get().intValue();
        array = copyPrimitiveArrayWithNewSize(array, overridingSize);
        int padding = requestedPrimitiveArrayPadding();
        if (overridingSize > originalSize) {
          for(int i = originalSize; i < overridingSize; i++) {
            setArrayAtIndexWithPadding(array, i, padding);
            //array[i] = pad;
          }
        }
      }
      // Truncate or expand bytes here.
      return newPrimitiveArrayWrapper(array);
    }

    protected abstract Optional<Integer> requestedPrimitiveArrayLength(YO stringSerializingOperation);

    protected abstract int requestedPrimitiveArrayPadding();

    protected abstract WA getPrimitiveArrayFromString(YO stringSerializingOperation);

    protected abstract int getPrimitiveArrayLength(WA array);

    protected abstract ST newPrimitiveArrayWrapper(WA primitiveArray);

    protected abstract WA copyPrimitiveArrayWithNewSize(WA array, int newSize);

    protected abstract void setArrayAtIndexWithPadding(WA array, int index, Number padding);

  }

  /*
  private ST disaggregateStringToPrimitiveArrayWrapper(
    SerializingField<ST,SD,?> primitiveArraySerializingField,
    YO stringSerializingOperation,
    int primitiveArraySerializingOperationIndex) {
    WA array = getPrimitiveArrayFromString(stringSerializingOperation);
    int originalSize = getPrimitiveArrayLength(array);
    Optional<Integer> overridingSizeRequest = stringSerializingOperation.effectiveRequestedPrimitiveArraySize();
    if (overridingSizeRequest.isPresent() && (overridingSizeRequest.get().intValue() != originalSize)) {
      int overridingSize = overridingSizeRequest.get().intValue();
      array = copyPrimitiveArrayWithNewSize(array, overridingSize);
      Number padding = stringSerializingOperation.effectiveRequestedPrimitiveArrayPadding();
      if (overridingSize > originalSize) {
        for(int i = originalSize; i < overridingSize; i++) {
          setArrayAtIndexWithPadding(array, i, padding);
          //array[i] = pad;
        }
      }
    }
    // Truncate or expand bytes here.
    return newPrimitiveArrayWrapper(array);
  }

  protected abstract WA getPrimitiveArrayFromString(YO stringSerializingOperation);

  protected abstract int getPrimitiveArrayLength(WA array);

  protected abstract ST newPrimitiveArrayWrapper(WA primitiveArray);

  protected abstract WA copyPrimitiveArrayWithNewSize(WA array, int newSize);

  protected abstract void setArrayAtIndexWithPadding(WA array, int index, Number padding);
  */

  @Override
  public final WkSzStructSubcomponent<XO, YO, SD> primitiveArray() {
    return this.primitiveArraySubcomponent.body();
  }

}
