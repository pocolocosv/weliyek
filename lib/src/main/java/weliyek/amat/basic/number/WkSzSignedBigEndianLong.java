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
package weliyek.amat.basic.number;

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat2.protocol.filter.FieldTester;

public class WkSzSignedBigEndianLong
    implements WkSzNumberDefinition<
                        Long,
                        WkSzSignedBigEndianLongReader>
{

  public static WkSzStruct<
                        Long,
                        OperationSettings,
                        WkSzSignedBigEndianLong,
                        WkSzSignedBigEndianLongReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        WkSzSignedBigEndianLong,
                        WkSzSignedBigEndianLongWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedBigEndianLong>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedBigEndianLong::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Long,
                        OperationSettings,?,?,
                        WkSzSignedBigEndianLong,
                        WkSzSignedBigEndianLongReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        WkSzSignedBigEndianLong,
                        WkSzSignedBigEndianLongWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedBigEndianLong,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedBigEndianLong(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Long,
                        WkSzSignedBigEndianLongReader,
                        WkSzSignedBigEndianLongWriter,
                        WkSzSignedBigEndianLong> definitionCore;

  private WkSzSignedBigEndianLong(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                            Long,
                            WkSzSignedBigEndianLongReader,
                            WkSzSignedBigEndianLongWriter,
                            WkSzSignedBigEndianLong>(
                                componentCore,
                                (i,xs,axb,xkc,dc) -> new WkSzSignedBigEndianLongReader(i,xs,axb,xkc,dc).operationCore,
                                BigEndianSignedLongInputSerialization.FACTORY,
                                (i,y,ys,ayb,ykc,dc) -> new WkSzSignedBigEndianLongWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                BigEndianLongOutputSerialization.FACTORY,
                                this,
                                Long.class);
  }

  @Override
  public Class<Long> rxClass() {
    return Long.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzSignedBigEndianLongReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
