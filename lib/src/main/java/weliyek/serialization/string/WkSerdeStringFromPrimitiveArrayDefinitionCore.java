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

import weliyek.serialization.WkSerdeDtreeAggregatorDefinitionCore;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;

public abstract class WkSerdeStringFromPrimitiveArrayDefinitionCore<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,?>,
                        XR extends WkResultSrlzPacketOperationData<String>,
                        XO extends WkSerdeStringFromPrimitiveArrayReader<XS,? extends WkDecodingRuntimeSrlzPacketOperationData<XB>,XR,XD,ST,SXD,SXO>,
                        XD extends WkSerdeStringFromPrimitiveArrayDefinition<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,?>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YO extends WkSerdeStringFromPrimitiveArrayWriter<YS,? extends WkEncodingRuntimeSrlzPacketOperationData<YB>,YR,YD,ST,SYD,SYO>,
                        YD extends WkSerdeStringFromPrimitiveArrayDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        ST extends WkPrimitiveArray<?,?>,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXO extends WkSerdeDtreePrimitiveArrayReader<ST,SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkSerdeDtreePrimitiveArrayWriter<ST,SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        SD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        D extends WkSerdeStringFromPrimitiveArrayDefinition<XO,YO,SD>,
                        DC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      ST,SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSerdeDtreeAggregatorDefinitionCore<String, XS, XB, XBC, XQC, XR, XD, XO, AXB, YS, YB, YBC, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSerdeStringFromPrimitiveArrayDefinition<XO, YO, SD>
{

  final WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,String,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
                primitiveArraySubcomponent;

  final Function<XO, String> primitiveArrayDeserializingStringAggregator;

  protected WkSerdeStringFromPrimitiveArrayDefinitionCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    BiFunction<YO, String, YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String primitiveArrayLabel,
    Optional<Predicate<? super XO>> primitiveArrayDeserializationEnablingTest,
    WkOperationSettingsFactory<XO,SXS> primitiveArrayDeserializingSettingsFactory,
    Function<XO, String> primitiveArrayDeserializingStringAggregator,
    Optional<Predicate<? super YO>> primitiveArraySerializationEnablingTest,
    WkOperationSettingsFactory<YO,SYS> primitiveArraySerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<ST, SYD, String, YO> primitiveArraySerializingDisaggregator,
    boolean primitiveArrayDeserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> primitiveArrayDefinitionFactory,
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
                          YO extends WkSerdeStringFromPrimitiveArrayWriter<?,?,?,?,ST,SD,?>,
                          ST extends WkPrimitiveArray<?,?>,
                          SD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                          WA>
      implements WkSzPacketWriteDisaggregator<ST, SD, String, YO>
  {

    @Override
    public ST disaggregate(
      WkSrlzOutputPacketFieldFrameNode<ST,SD,?> primitiveArraySerializingField,
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
  public final WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD> primitiveArray() {
    return this.primitiveArraySubcomponent.body();
  }

}
