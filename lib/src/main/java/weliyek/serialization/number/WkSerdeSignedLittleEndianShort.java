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
package weliyek.serialization.number;

import java.util.List;

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.sequence.WkSerdeDtreeShortSignedLittleEndianWriterEncoderEngine;

public class WkSerdeSignedLittleEndianShort
    implements WkSerdeDtreeNumberStructDefinition<Short>
{

  public static WkSerdeDtreeStruct<
                      Short,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedLittleEndianShort,
                      WkSerdeSignedLittleEndianShortReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedLittleEndianShort,
                      WkSerdeSignedLittleEndianShortWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeSignedLittleEndianShort>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedLittleEndianShort::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Short,
                        WkSerdeSignedLittleEndianShortReader,
                        WkSerdeSignedLittleEndianShortWriter,
                        WkSerdeSignedLittleEndianShort>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedLittleEndianShort(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Short,
                        WkSerdeSignedLittleEndianShortReader,
                        WkSerdeSignedLittleEndianShortWriter,
                        WkSerdeSignedLittleEndianShort> definitionCore;

  public WkSerdeSignedLittleEndianShort(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Short,
                                  WkSerdeSignedLittleEndianShortReader,
                                  WkSerdeSignedLittleEndianShortWriter,
                                  WkSerdeSignedLittleEndianShort>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeSignedLittleEndianShortReader(i,xkc,dc).operationCore,
                                      WkSerdeDtreeShortSignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeSignedLittleEndianShortWriter(i,ykc,dc).operationCore,
                                      WkSerdeDtreeShortSignedLittleEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Short.class);
  }

  @Override
  public Class<Short> serializableClass() {
    return Short.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

}
