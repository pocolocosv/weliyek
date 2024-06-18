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

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;

public abstract class WkSerdeStringFromBytesDefinitionCore<
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XO extends WkSerdeStringFromBytesReader<XS,? extends WkSerdeDtreeOperationInputRuntime<XB>,XR,XD,SXD,SXO>,
                        XOC extends WkSerdeStringFromBytesReaderCore<
                                        XS,XB,XBC,?,XQC,XR,XO,?,XD,XDC,AXB,SXS,SXO,SXD>,
                        XD extends WkSerdeStringFromBytesDefinition<XO,?,? extends SXD>,
                        XDC extends WkSerdeStringFromBytesDefinitionCore<
                                        XS,XB,XBC,XQC,XR,XO,XOC,XD,?,AXB,
                                        ?,?,?,?,?,?,?,?,?,?,
                                        SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD,?>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<String>,
                        YO extends WkSerdeStringFromBytesWriter<YS,? extends WkSerdeDtreeOperationOutputRuntime<YB>,YR,YD,SYD,SYO>,
                        YOC extends WkSerdeStringFromBytesWriterCore<
                                        YS,YB,YBC,?,YQC,YR,YO,?,YD,YDC,AYB,SYS,SYO,SYD>,
                        YD extends WkSerdeStringFromBytesDefinition<?,YO,? extends SYD>,
                        YDC extends WkSerdeStringFromBytesDefinitionCore<
                                        ?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YO,YOC,YD,?,AYB,
                                        ?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreeByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreeByteArrayDefinition,
                        SD extends WkSerdeDtreeByteArrayDefinition,
                        D extends WkSerdeStringFromBytesDefinition<XO,YO,SD>,
                        DC extends WkSerdeStringFromBytesDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XOC,XD,XDC,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YOC,YD,YDC,AYB,
                                      SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                        XS, XB, XBC, XQC, XR, XO, XOC, XD, XDC, AXB,
                        YS, YB, YBC, YQC, YR, YO, YOC, YD, YDC, AYB,
                        WkByteArray, SXS, SXO, SXD, SYS, SYO, SYD, SD, D, DC>
    implements WkSerdeStringFromBytesDefinition<XO, YO, SD>
{

  private final Charset defaultCharset;

  protected WkSerdeStringFromBytesDefinitionCore(
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<XS,XDC,XOC,AXB> readingOpFactory,
    Function<AYB,YQC> txRuntimeFactory,
    BiFunction<YO,String,YR> txResultFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<String,YS,YDC,YOC,AYB> writingOpFactory,
    String bytesLabel,
    Optional<Predicate<? super XO>> bytesDeserializationEnablingTest,
    WkOperationSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO, String> bytesDeserializingStringAggregator,
    Optional<Predicate<? super YO>> bytesSerializationEnablingTest,
    WkOperationSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<WkByteArray, SYD, String, YO> bytesSerializingDisaggregator,
    boolean bytesDeserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                WkByteArray,SXS,?,?,SXD,?,SXO,?,XBC,SYS,?,?,SYD,?,SYO,?,YBC,SD,?>> bytesDefinitionFactory,
    D definitionBody) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          bytesLabel,
          bytesDeserializationEnablingTest,
          bytesDeserializingSettingsFactory,
          bytesDeserializingStringAggregator,
          bytesSerializationEnablingTest,
          bytesSerializingSettingsFactory,
          bytesSerializingDisaggregator,
          bytesDeserializedRequired,
          bytesDefinitionFactory,
          definitionBody);
    this.defaultCharset = Objects.requireNonNull(defaultCharset);
  }

  public abstract static class ByteArrayFromStringDisaggregator<
                        YO extends WkSerdeStringFromBytesWriter<?,?,?,?,SD,?>,
                        SD extends WkSerdeDtreeByteArrayDefinition>
      extends PrimitiveArrayDisaggregatorFromString<
                        YO,
                        WkByteArray,
                        SD,
                        byte[]>
  {

    @Override
    protected final byte[] getPrimitiveArrayFromString(YO stringSerializingOperation) {
      Charset charset = stringSerializingOperation.charset();
      String string = stringSerializingOperation.serializable();
      return string.getBytes(charset);
    }

    @Override
    protected final int getPrimitiveArrayLength(byte[] array) {
      return array.length;
    }

    @Override
    protected final WkByteArray newPrimitiveArrayWrapper(byte[] primitiveArray) {
      return new WkByteArray(primitiveArray);
    }

    @Override
    protected final byte[] copyPrimitiveArrayWithNewSize(byte[] array, int newSize) {
      return Arrays.copyOf(array, newSize);
    }

    @Override
    protected final void setArrayAtIndexWithPadding(byte[] array, int index, Number padding) {
      array[ index ] = padding.byteValue();
    }

  }

  @Override
  public final WkSerdeDtreeStructSubfield<XO, YO, SD> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return this.defaultCharset;
  }

}
