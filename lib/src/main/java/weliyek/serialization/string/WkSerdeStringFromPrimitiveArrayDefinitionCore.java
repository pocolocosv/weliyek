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

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;

public abstract class WkSerdeStringFromPrimitiveArrayDefinitionCore<
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XO extends WkSerdeStringFromPrimitiveArrayReader<XS,? extends WkSerdeDtreeOperationInputRuntime<XB>,XR,XD,ST,SXD,SXO>,
                        XOC extends WkSerdeStringFromPrimitiveArrayReaderCore<
                                      XS,XB,XBC,?,XQC,XR,XO,?,XD,XDC,AXB,ST,SXS,SXO,SXD>,
                        XD extends WkSerdeStringFromPrimitiveArrayDefinition<XO,?,? extends SXD>,
                        XDC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XOC,XD,?,AXB,
                                      ?,?,?,?,?,?,?,?,?,?,
                                      ST,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD,?>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<String>,
                        YO extends WkSerdeStringFromPrimitiveArrayWriter<YS,? extends WkSerdeDtreeOperationOutputRuntime<YB>,YR,YD,ST,SYD,SYO>,
                        YOC extends WkSerdeStringFromPrimitiveArrayWriterCore<
                                        YS,YB,YBC,?,YQC,YR,YO,?,YD,YDC,AYB,ST,SYS,SYO,SYD>,
                        YD extends WkSerdeStringFromPrimitiveArrayDefinition<?,YO,? extends SYD>,
                        YDC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                        ?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YO,YOC,YD,?,AYB,
                                        ST,?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ST extends WkPrimitiveArray<?,?>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreePrimitiveArrayReader<ST,SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreePrimitiveArrayWriter<ST,SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        SD extends WkSerdeDtreePrimitiveArrayDefinition<ST>,
                        D extends WkSerdeStringFromPrimitiveArrayDefinition<XO,YO,SD>,
                        DC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XOC,XD,XDC,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YOC,YD,YDC,AYB,
                                      ST,SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSerdeDtreeAggregatorStructDefinitionCore<
                        String, XS, XB, XBC, XQC, XR, XD, XDC, XO, XOC, AXB,
                        YS, YB, YBC, YQC, YR, YD, YDC, YO, YOC, AYB, D, DC>
    implements WkSerdeStringFromPrimitiveArrayDefinition<XO, YO, SD>
{

  final WkSerdeDtreeStructSubfieldCore<ST,String,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
                primitiveArraySubcomponent;

  final Function<XO, String> primitiveArrayDeserializingStringAggregator;

  protected WkSerdeStringFromPrimitiveArrayDefinitionCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<XS,XDC,XOC,AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    BiFunction<YO, String, YR> txResultFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<String,YS,YDC,YOC,AYB> writingOpFactory,
    String primitiveArrayLabel,
    Optional<Predicate<? super XO>> primitiveArrayDeserializationEnablingTest,
    WkOperationSettingsFactory<XO,SXS> primitiveArrayDeserializingSettingsFactory,
    Function<XO, String> primitiveArrayDeserializingStringAggregator,
    Optional<Predicate<? super YO>> primitiveArraySerializationEnablingTest,
    WkOperationSettingsFactory<YO,SYS> primitiveArraySerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<ST, SYD, String, YO> primitiveArraySerializingDisaggregator,
    boolean primitiveArrayDeserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXO,XBC,SYS,SYO,YBC,SD> primitiveArrayDefinitionFactory,
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
      WkSerdeDtreeMsgOutputField<ST,SD,?> primitiveArraySerializingField,
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

  @Override
  public final WkSerdeDtreeStructSubfield<XO, YO, SD> primitiveArray() {
    return this.primitiveArraySubcomponent.asProtocolField();
  }

}
