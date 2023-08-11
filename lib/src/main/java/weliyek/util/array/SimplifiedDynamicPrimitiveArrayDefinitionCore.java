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
package weliyek.util.array;

import java.util.function.IntFunction;

import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzBasicReadingResult;
import weliyek.serialization.WkSzBasicReadingRuntime;
import weliyek.serialization.WkSzBasicWritingResult;
import weliyek.serialization.WkSzBasicWritingRuntime;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.number.WkSzNumberWriter;

public final class SimplifiedDynamicPrimitiveArrayDefinitionCore<
                        T extends PrimitiveArrayWrapper<?,?>,
                        XD extends WkSzDynamicPrimitiveArrayDefinition<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends DynamicPrimitiveArrayDeserializing<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkSzDynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends DynamicPrimitiveArraySerializing<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<
                                        ZT,
                                        WkSzOperationSettings,?,
                                        ?,ZXD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<
                                        ZT,
                                        WkSzOperationSettings,?,?,ZYD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>,
                        VXD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>,
                        VXO extends WkSzVariableSizePrimitiveArrayReader<
                                        T,WkSzVariableLengthOperationSettings,?,?,VXD>,
                        VYD extends WkSzVariableSizePrimitiveArrayDefinition<T,?>,
                        VYO extends WkSzVariableSizePrimitiveArrayWriter<
                                        T,WkSzOperationSettings,?,?,VYD>,
                        VD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>,
                        D extends WkSzDynamicPrimitiveArrayDefinition<T,XO,YO,ZD,VD>>
    extends DynamicSequenceDefinitionCore<
                        T,
                        WkSzOperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZXO, ZXD,
                        WkSzOperationSettings,
                        ZYO, ZYD,
                        ZD,
                        WkSzVariableLengthOperationSettings,
                        VXO, VXD,
                        WkSzOperationSettings,
                        VYO, VYD,
                        VD,
                        D,
                        SimplifiedDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>>
    implements WkSzDynamicPrimitiveArrayDefinition<T, XO, YO, ZD, VD>
{

  protected SimplifiedDynamicPrimitiveArrayDefinitionCore(
    String sizeComponentLabel,
    IntFunction<ZT> sizeComponentIntToNumber,
    ProtocolDefinitionFactory<
      ZT,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    ProtocolDefinitionFactory<
      T,WkSzVariableLengthOperationSettings,VXD,VXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      VYD,VYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,VD> varseqComponentDefinitionFactory,
    WkSzPacketReaderOperationCoreFactory<
      T,WkSzOperationSettings,XD,
      SimplifiedDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      T,WkSzOperationSettings,YD,
      SimplifiedDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          sizeComponentLabel,
          WkSzOperationSettings::none,
          WkSzOperationSettings::none,
          (zk,yo,i) -> sizeComponentIntToNumber.apply(yo.serializable().getLength()), // Set size component value from wrapper length.
          sizeComponentDefinitionFactory,
          varseqComponentLabel,
          (i,xo) -> {
            ZT sizeNumber = xo.size().field().get()
                                     .firstOperation().get()
                                     .result().get()
                                     .deserialized().get();
            return WkSzVariableLengthOperationSettings.withLength(sizeNumber.intValue());
          },
          WkSzOperationSettings::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          WkSzBasicReadingRuntime::new,
          WkSzBasicReadingResult::new,
          readingOpFactory,
          WkSzBasicWritingRuntime::new,
          WkSzBasicWritingResult::empty,
          writingOpFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  SimplifiedDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>
  getThis() {
    return this;
  }

  @Override
  public int extractLengthFromSerializablesSequence(T sequence) {
    return definition().extractLengthFromSerializablesSequence(sequence);
  }

}
