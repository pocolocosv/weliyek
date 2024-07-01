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
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public final class WkSerdeSignedBigEndianIntegerWriter
        implements WkSerdeDtreeNumberMsgWriter<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<Integer>,
                        WkSerdeSignedBigEndianInteger>
{

  final WkSerdeDtreeNumberMsgWriterCoreSimplified<
                      Integer,
                      WkSerdeSignedBigEndianIntegerWriter,
                      WkSerdeSignedBigEndianInteger> writerCore;

  WkSerdeSignedBigEndianIntegerWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<Integer,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writeFieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Integer,?,WkSerdeSignedBigEndianIntegerWriter,WkSerdeSignedBigEndianInteger> definitionCore) {
    writerCore = new WkSerdeDtreeNumberMsgWriterCoreSimplified<>(
                                  index,
                                  writeFieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.writerCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.writerCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Integer>> result() {
    return this.writerCore.result();
  }

  @Override
  public int index() {
    return this.writerCore.index();
  }

  @Override
  public Integer serializable() {
    return this.writerCore.serializable();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.writerCore.parentField();
  }

  @Override
  public WkSerdeSignedBigEndianInteger definition() {
    return this.writerCore.definition();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?,?,?>> subfields() {
    return this.writerCore.subfields();
  }

  @Override
  public String name() {
    return this.writerCore.name();
  }

  @Override
  public String toString() {
    return this.writerCore.toString();
  }

}
