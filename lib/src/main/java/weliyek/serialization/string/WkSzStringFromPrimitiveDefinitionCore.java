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
package weliyek.serialization.string;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.OperationSubsegmentSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzAggregatorDefinitionCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzSubcomponentCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.util.array.PrimitiveArrayReading;
import weliyek.util.array.PrimitiveArrayWrapper;
import weliyek.util.array.PrimitiveArrayWriting;
import weliyek.util.array.WkSzPrimitiveArrayDefinition;

public abstract class WkSzStringFromPrimitiveDefinitionCore<
                        XS extends WkSzOperationSettings,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkSzReadingRuntimeControl<XB,XBC,?>,
                        XR extends WkSzReadingResult<String>,
                        XO extends WkSzStringFromPrimitiveReader<XS,? extends WkSzReadingRuntime<XB>,XR,XD,ST,SXD,SXO>,
                        XD extends WkSzStringFromPrimitiveDefinition<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,?>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzStringFromPrimitiveWriter<YS,? extends WkSzWritingRuntime<YB>,YR,YD,ST,SYD,SYO>,
                        YD extends WkSzStringFromPrimitiveDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        ST extends PrimitiveArrayWrapper<?,?>,
                        SXS extends WkSzOperationSettings,
                        SXO extends PrimitiveArrayReading<ST,SXS,?,?,SXD>,
                        SXD extends WkSzPrimitiveArrayDefinition<ST,SXO>,
                        SYS extends WkSzOperationSettings,
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
    WkSzPacketReaderOperationCoreFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String primitiveArrayLabel,
    Optional<Predicate<? super XO>> primitiveArrayDeserializationEnablingTest,
    OperationSubsegmentSettingsFactory<XO,SXS> primitiveArrayDeserializingSettingsFactory,
    Function<XO, String> primitiveArrayDeserializingStringAggregator,
    Optional<Predicate<? super YO>> primitiveArraySerializationEnablingTest,
    OperationSubsegmentSettingsFactory<YO,SYS> primitiveArraySerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<ST, SYD, String, YO> primitiveArraySerializingDisaggregator,
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
      implements WkSzPacketWriteDisaggregator<ST, SD, String, YO>
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