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

import java.nio.charset.Charset;
import java.util.Arrays;
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
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayReading;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.ByteArrayWriting;

public abstract class WkSzStringFromBytesDefinitionCore<
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,?>,
                        XR extends DeserializingResult<String>,
                        XO extends WkSzStringFromBytesReader<XS,? extends DeserializingRuntime<XB>,XR,XD,SXD,SXO>,
                        XD extends WkSzStringFromBytesDefinition<XO,?,? extends SXD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,?>,
                        YR extends SerializingResult,
                        YO extends WkSzStringFromBytesWriter<YS,? extends SerializingRuntime<YB>,YR,YD,SYD,SYO>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        SXS extends OperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>,
                        SYS extends OperationSettings,
                        SYO extends ByteArrayWriting<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>,
                        SD extends WkSzByteArrayDefinition<SXO>,
                        D extends WkSzStringFromBytesDefinition<XO,YO,SD>,
                        DC extends WkSzStringFromBytesDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      SXS,SXO,SXD,SYS,SYO,SYD,SD,D,?>>
    extends WkSzStringFromPrimitiveDefinitionCore<XS, XB, XBC, XQC, XR, XO, XD, AXB, YS, YB, YBC, YQC, YR, YO, YD, AYB, ByteArrayWrapper, SXS, SXO, SXD, SYS, SYO, SYD, SD, D, DC>
    implements WkSzStringFromBytesDefinition<XO, YO, SD>
{

  private final Charset defaultCharset;

  protected WkSzStringFromBytesDefinitionCore(
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,String,XR> rxResultFactory,
    PacketInputFieldReadingFactory<String,XS,XD,DC,XO,AXB> readingOpFactory,
    Function<AYB,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    PacketOutputFieldWritingFactory<String,YS,YD,DC,YO,AYB> writingOpFactory,
    String bytesLabel,
    Optional<Predicate<? super XO>> bytesDeserializationEnablingTest,
    OperationSubsegmentSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO, String> bytesDeserializingStringAggregator,
    Optional<Predicate<? super YO>> bytesSerializationEnablingTest,
    OperationSubsegmentSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    Disaggregator<ByteArrayWrapper, SYD, String, YO> bytesSerializingDisaggregator,
    boolean bytesDeserializedRequired,
    ProtocolDefinitionFactory<ByteArrayWrapper,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> bytesDefinitionFactory,
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
                        ByteArrayWrapper,
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
    protected final ByteArrayWrapper newPrimitiveArrayWrapper(byte[] primitiveArray) {
      return new ByteArrayWrapper(primitiveArray);
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
