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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;

public abstract class WkSerdeStringFromBytesDefinitionCore<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,?>,
                        XR extends WkResultSrlzPacketOperationData<String>,
                        XO extends WkSerdeStringFromBytesReader<XS,? extends WkDecodingRuntimeSrlzPacketOperationData<XB>,XR,XD,SXD,SXO>,
                        XD extends WkSerdeStringFromBytesDefinition<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,?>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YO extends WkSerdeStringFromBytesWriter<YS,? extends WkEncodingRuntimeSrlzPacketOperationData<YB>,YR,YD,SYD,SYO>,
                        YD extends WkSerdeStringFromBytesDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXO extends WkSerdeDtreeByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkSerdeDtreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreeByteArrayDefinition,
                        SD extends WkSerdeDtreeByteArrayDefinition,
                        D extends WkSerdeStringFromBytesDefinition<XO,YO,SD>,
                        DC extends WkSerdeStringFromBytesDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSerdeStringFromPrimitiveArrayDefinitionCore<XS, XB, XBC, XQC, XR, XO, XD, AXB, YS, YB, YBC, YQC, YR, YO, YD, AYB, WkByteArray, SXS, SXO, SXD, SYS, SYO, SYD, SD, D, DC>
    implements WkSerdeStringFromBytesDefinition<XO, YO, SD>
{

  private final Charset defaultCharset;

  protected WkSerdeStringFromBytesDefinitionCore(
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB,YQC> txRuntimeFactory,
    BiFunction<YO,String,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String bytesLabel,
    Optional<Predicate<? super XO>> bytesDeserializationEnablingTest,
    WkOperationSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO, String> bytesDeserializingStringAggregator,
    Optional<Predicate<? super YO>> bytesSerializationEnablingTest,
    WkOperationSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<WkByteArray, SYD, String, YO> bytesSerializingDisaggregator,
    boolean bytesDeserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<WkByteArray,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> bytesDefinitionFactory,
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
  public final WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return this.defaultCharset;
  }

}
