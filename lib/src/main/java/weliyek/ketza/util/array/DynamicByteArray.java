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

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;
import weliyek.amat.basic.number.NumberSerializing;
import weliyek.amat2.protocol.filter.FieldTester;

public class DynamicByteArray<
                        ZT extends Number,
                        ZXD extends NumberDefinition<ZT,ZXO>,
                        ZXO extends NumberDeserializing<ZT,OperationSettings,?,?,ZXD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        ZYO extends NumberSerializing<ZT,OperationSettings,?,?,ZYD>,
                        ZD extends NumberDefinition<ZT,ZXO>>
    implements ByteArrayDefinition<
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>>,
               DynamicPrimitiveArrayDefinition<
                        ByteArrayWrapper,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        ZD,
                        VariableSizeByteArray>
{
  public static <ZX extends Number,
                 ZXD extends NumberDefinition<ZX,ZXO>,
                 ZXO extends NumberDeserializing<ZX,OperationSettings,?,?,ZXD>,
                 ZYD extends NumberDefinition<ZX,?>,
                 ZYO extends NumberSerializing<ZX,OperationSettings,?,?,ZYD>,
                 ZD extends NumberDefinition<ZX,ZXO>>
  PacketStructure<ByteArrayWrapper,
                  OperationSettings,
                  DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
                  InputBytestreamGeneralBase<?>,
                  OperationSettings,
                  DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
                  OutputBytestreamGeneralBase<?>,
                  DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newPacketStructure(
    String label,
    String sizeLabel,
    String bytearrayLabel,
    int minLength,
    int maxLength,
    IntFunction<ZX> sizeWrapper,
    ProtocolDefinitionFactory<
      ZX,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<?>,
      OperationSettings,ZYD,ZYO,OutputBytestreamGeneralBase<?>,ZD>  sizeDefinition)
  {
    return new PacketStructure<ByteArrayWrapper,
        OperationSettings,
        DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
        DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
        InputBytestreamGeneralBase<?>,
        OperationSettings,
        DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
        DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
        OutputBytestreamGeneralBase<?>,
        DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>(
                  label,
                  (pc) -> DynamicByteArray.newCore(
                              pc, sizeLabel, sizeWrapper, bytearrayLabel, minLength, maxLength, sizeDefinition),
                  CountingInputBytestream::new,
                  CountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends NumberDefinition<ZX,ZXO>,
                 ZXO extends NumberDeserializing<ZX,OperationSettings,?,?,ZXD>,
                 ZYD extends NumberDefinition<ZX,?>,
                 ZYO extends NumberSerializing<ZX,OperationSettings,?,?,ZYD>,
                 ZD extends NumberDefinition<ZX,ZXO>>
  DefinitionSegmentCore<ByteArrayWrapper,
                        OperationSettings,?,?,
                        DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
                        OutputBytestreamGeneralBase<?>,
                        DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZX> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    ProtocolDefinitionFactory<
      ZX,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<?>,
      OperationSettings,ZYD,ZYO,OutputBytestreamGeneralBase<?>,ZD> sizeDefinitionFactory)
  {
    return new DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(componentCore, sizeLabel, wrapperSizeGetter, arrayLabel, minLength, maxLength, sizeDefinitionFactory).definitionCore;
  }

  private final SimplifiedDynamicPrimitiveArrayDefinitionCore<
                        ByteArrayWrapper,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                        VariableSizeByteArray,
                        VariableSizeByteArrayDeserializing,
                        VariableSizeByteArray,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray,
                        DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>> definitionCore;

  DynamicByteArray(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    ProtocolDefinitionFactory<
      ZT,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<?>,OperationSettings,
      ZYD,ZYO,OutputBytestreamGeneralBase<?>,ZD> sizeComponentDefinitionFactory) {
    this.definitionCore = new SimplifiedDynamicPrimitiveArrayDefinitionCore<
                                ByteArrayWrapper,
                                DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                                VariableSizeByteArray,
                                VariableSizeByteArrayDeserializing,
                                VariableSizeByteArray,
                                VariableSizeByteArraySerializing,
                                VariableSizeByteArray,
                                DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                    sizeLabel,
                                    wrapperSizeGetter,
                                    sizeComponentDefinitionFactory,
                                    arrayLabel,
                                    (pc) -> VariableSizeByteArray.newCore(minLength, maxLength, pc),
                                    (i,xs,axb,xkc,dc) -> new DynamicByteArrayDeserializing<ZT,ZXO,ZXD>(i,xs,axb,xkc,dc).operationCore,
                                    (i,y,ys,ayb,ykc,dc) -> new DynamicByteArraySerialzing<ZT,ZYO,ZYD>(i,y,ys,ayb,ykc,dc).operationCore,
                                    componentCore,
                                    this,
                                    ByteArrayWrapper.class);
  }

  @Override
  public Class<ByteArrayWrapper> rxClass() {
    return ByteArrayWrapper.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?> makeTester(
    Predicate<? super DynamicByteArrayDeserializing<ZT, ZXO, ZXD>> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
      SubcomponentHandler<DynamicByteArrayDeserializing<ZT, ZXO, ZXD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>, ZD>
      size() {
    return this.definitionCore.size();
  }

  @Override
  public
      SubcomponentHandler<DynamicByteArrayDeserializing<ZT, ZXO, ZXD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>, VariableSizeByteArray>
      variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
