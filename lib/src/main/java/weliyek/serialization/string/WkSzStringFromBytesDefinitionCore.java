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

import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.OperationSubsegmentSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.util.array.WkSzByteArrayReader;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzByteArrayWriter;
import weliyek.util.array.WkSzByteArrayDefinition;

public abstract class WkSzStringFromBytesDefinitionCore<
                        XS extends WkSzOperationSettings,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkSzReadingRuntimeControl<XB,XBC,?>,
                        XR extends WkSzReadingResult<String>,
                        XO extends WkSzStringFromBytesReader<XS,? extends WkSzReadingRuntime<XB>,XR,XD,SXD,SXO>,
                        XD extends WkSzStringFromBytesDefinition<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,?>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzStringFromBytesWriter<YS,? extends WkSzWritingRuntime<YB>,YR,YD,SYD,SYO>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SXS extends WkSzOperationSettings,
                        SXO extends WkSzByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>,
                        SYS extends WkSzOperationSettings,
                        SYO extends WkSzByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>,
                        SD extends WkSzByteArrayDefinition<SXO>,
                        D extends WkSzStringFromBytesDefinition<XO,YO,SD>,
                        DC extends WkSzStringFromBytesDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSzStringFromPrimitiveDefinitionCore<XS, XB, XBC, XQC, XR, XO, XD, AXB, YS, YB, YBC, YQC, YR, YO, YD, AYB, WkByteArray, SXS, SXO, SXD, SYS, SYO, SYD, SD, D, DC>
    implements WkSzStringFromBytesDefinition<XO, YO, SD>
{

  private final Charset defaultCharset;

  protected WkSzStringFromBytesDefinitionCore(
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String bytesLabel,
    Optional<Predicate<? super XO>> bytesDeserializationEnablingTest,
    OperationSubsegmentSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO, String> bytesDeserializingStringAggregator,
    Optional<Predicate<? super YO>> bytesSerializationEnablingTest,
    OperationSubsegmentSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<WkByteArray, SYD, String, YO> bytesSerializingDisaggregator,
    boolean bytesDeserializedRequired,
    ProtocolDefinitionFactory<WkByteArray,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> bytesDefinitionFactory,
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
                        YO extends WkSzStringFromBytesWriter<?,?,?,?,SD,?>,
                        SD extends WkSzByteArrayDefinition<?>>
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
  public final WkSzStructSubcomponent<XO, YO, SD> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return this.defaultCharset;
  }

}
