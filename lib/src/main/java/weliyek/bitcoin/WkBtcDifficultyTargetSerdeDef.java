/*
 * Copyright (C) 2024  Ricardo Villalobos - All Rights Reserved
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
package weliyek.bitcoin;

import java.util.List;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;

public class WkBtcDifficultyTargetSerdeDef
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcDifficultyTarget>
{

  static WkSerdeDtreeStruct<
                        WkBtcDifficultyTarget,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcDifficultyTargetSerdeDef>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
        label,
        WkBtcDifficultyTargetSerdeDef::newCore,
        WkSerdeDtreeBytestreamCountingInputStream::new,
        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }


  public static WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcDifficultyTarget,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeWriter,
                        WkBtcDifficultyTargetSerdeDef>
  newCore(
      WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    return new WkBtcDifficultyTargetSerdeDef(fieldCore).definitionCore;
  }

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcDifficultyTarget,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcDifficultyTargetSerdeDef,
                        WkBtcDifficultyTargetSerdeWriter,
                        WkBtcDifficultyTargetSerdeDef> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                        Integer,
                        WkBtcDifficultyTarget,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerReader,
                        ? extends WkSerdeDtreeBytestreamInputBase<?>,
                        WkBtcDifficultyTargetSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcDifficultyTargetSerdeWriter,
                        WkSerdeSignedLittleEndianInteger> int32;

  public WkBtcDifficultyTargetSerdeDef(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<>(
                                  WkBtcDifficultyTargetSerdeReader::newCore,
                                  WkBtcDifficultyTargetSerdeWriter::newCore,
                                  fieldCore,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkBtcDifficultyTargetSerdeDef::onReadFull,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnWriteInit,
                                  this,
                                  WkBtcDifficultyTarget.class);
    this.int32 = WkSerdeSignedLittleEndianInteger.
                    addAsSubfieldWithSingleOperation(
                        "INT32",
                        (k,ao,i) -> ao.serializable().toCompact(),
                        definitionCore);
  }

  public WkSerdeDtreeStructSubfield<WkBtcDifficultyTargetSerdeReader, WkBtcDifficultyTargetSerdeWriter, WkSerdeSignedLittleEndianInteger> int32() {
    return this.int32.asProtocolField();
  }

  private static WkBtcDifficultyTarget onReadFull(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
    WkBtcDifficultyTarget,
    WkSerdeDtreeOperationSettings,
    WkBtcDifficultyTargetSerdeDef,
    WkBtcDifficultyTargetSerdeReader> readerCore) {
    int compactVal = readerCore.body().int32().get().firstOperation().get().result().get().serializable().get().intValue();
    return WkBtcDifficultyTarget.ofCompact(compactVal);
  }

  @Override
  public Class<WkBtcDifficultyTarget> serializableClass() {
    return WkBtcDifficultyTarget.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
