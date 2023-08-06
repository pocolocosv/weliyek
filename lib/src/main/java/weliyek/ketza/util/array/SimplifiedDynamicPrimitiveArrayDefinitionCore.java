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
package weliyek.ketza.util.array;

import java.util.function.IntFunction;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.BasicReadingRuntime;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.number.WkSzNumberWriter;

public final class SimplifiedDynamicPrimitiveArrayDefinitionCore<
                        T extends PrimitiveArrayWrapper<?,?>,
                        XD extends WkSzDynamicPrimitiveArrayDefinition<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends DynamicPrimitiveArrayDeserializing<
                                        T,
                                        OperationSettings,
                                        DeserializingRuntime<WkSzInputBytestream>,
                                        DeserializingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkSzDynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends DynamicPrimitiveArraySerializing<
                                        T,
                                        OperationSettings,
                                        SerializingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<
                                        ZT,
                                        OperationSettings,?,
                                        ?,ZXD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<
                                        ZT,
                                        OperationSettings,?,?,ZYD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>,
                        VXD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>,
                        VXO extends WkSzVariableSizePrimitiveArrayReader<
                                        T,VariableLengthSettings,?,?,VXD>,
                        VYD extends WkSzVariableSizePrimitiveArrayDefinition<T,?>,
                        VYO extends WkSzVariableSizePrimitiveArrayWriter<
                                        T,OperationSettings,?,?,VYD>,
                        VD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>,
                        D extends WkSzDynamicPrimitiveArrayDefinition<T,XO,YO,ZD,VD>>
    extends DynamicSequenceDefinitionCore<
                        T,
                        OperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        ReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          DeserializingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SerializingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        OperationSettings,
                        ZXO, ZXD,
                        OperationSettings,
                        ZYO, ZYD,
                        ZD,
                        VariableLengthSettings,
                        VXO, VXD,
                        OperationSettings,
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
      ZT,OperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,OperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    ProtocolDefinitionFactory<
      T,VariableLengthSettings,VXD,VXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,OperationSettings,
      VYD,VYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,VD> varseqComponentDefinitionFactory,
    PacketInputFieldReadingFactory<
      T,OperationSettings,XD,
      SimplifiedDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    PacketOutputFieldWritingFactory<
      T,OperationSettings,YD,
      SimplifiedDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          sizeComponentLabel,
          OperationSettings::none,
          OperationSettings::none,
          (zk,yo,i) -> sizeComponentIntToNumber.apply(yo.serializable().getLength()), // Set size component value from wrapper length.
          sizeComponentDefinitionFactory,
          varseqComponentLabel,
          (i,xo) -> {
            ZT sizeNumber = xo.size().field().get()
                                     .firstOperation().get()
                                     .result().get()
                                     .deserialized().get();
            return VariableLengthSettings.withLength(sizeNumber.intValue());
          },
          OperationSettings::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          BasicReadingRuntime::new,
          BasicReadingResult::new,
          readingOpFactory,
          BasicWritingRuntime::new,
          BasicWritingResult::empty,
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
