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
import java.util.Optional;
import java.util.function.Function;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.WkSzBasicReadingRuntime;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.WkSzReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.WkSzBasicWritingRuntime;
import weliyek.amat.base.output.Disaggregator;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzWritingRuntimeControl;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.base.WkSzStructComponentCoreBase;
import weliyek.ketza.util.array.ByteArrayReading;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.ByteArrayWriting;

public class SimplifiedStringFromBytesCore<
                        XS extends WkSzOperationSettings,
                        XO extends WkSzStringFromBytesReader<
                                        XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<String>,
                                        XD,
                                        SXD,
                                        SXO>,
                        XD extends WkSzStringFromBytesDefinition<XO,?,? extends SXD>,
                        YS extends WkSzOperationSettings,
                        YO extends WkSzStringFromBytesWriter<
                                        YS,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,
                                        SYD,
                                        SYO>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        SXS extends WkSzOperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>,
                        SYS extends WkSzOperationSettings,
                        SYO extends ByteArrayWriting<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>,
                        SD extends WkSzByteArrayDefinition<SXO>,
                        D extends WkSzStringFromBytesDefinition<XO,YO,SD>>
    extends WkSzStringFromBytesDefinitionCore<
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<String>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        SXS,
                        SXO, SXD,
                        SYS,
                        SYO, SYD,
                        SD,
                        D,
                        SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>>
{

  protected SimplifiedStringFromBytesCore(
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    PacketInputFieldReadingFactory<
      String,XS,XD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      XO,WkSzInputBytestreamBase<?>>
        readingOpFactory,
    PacketOutputFieldWritingFactory<
      String,YS,YD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      YO,WkSzOutputBytestreamBase<?>>
        writingOpFactory,
    String bytesLabel,
    OperationSubsegmentSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO,String> bytesDeserializingStringAggregator,
    OperationSubsegmentSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    Disaggregator<ByteArrayWrapper,SYD,String,YO> bytesSerializingDisaggregator,
    ProtocolDefinitionFactory<
      ByteArrayWrapper,SXS,SXD,SXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
      SYS,SYD,SYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,SD>
        bytesDefinitionFactory,
    D definitionBody) {
    super(
          defaultCharset,
          componentCore,
          WkSzBasicReadingRuntime::new,
          BasicReadingResult::new,
          readingOpFactory,
          WkSzBasicWritingRuntime::new,
          BasicWritingResult::empty,
          writingOpFactory,
          bytesLabel,
          Optional.empty(),
          bytesDeserializingSettingsFactory,
          bytesDeserializingStringAggregator,
          Optional.empty(),
          bytesSerializingSettingsFactory,
          bytesSerializingDisaggregator,
          false,
          bytesDefinitionFactory,
          definitionBody);
  }

  @Override
  protected SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>
  getThis() {
    return this;
  }

}
