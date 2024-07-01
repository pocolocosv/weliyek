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

public final class WkSerdeUnsignedByteWriter
    implements WkSerdeDtreeNumberMsgWriter<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<Integer>,
                        WkSerdeUnsignedByte>
{

  final WkSerdeDtreeNumberMsgWriterCoreSimplified<
                        Integer,
                        WkSerdeUnsignedByteWriter,
                        WkSerdeUnsignedByte> writingCore;

  WkSerdeUnsignedByteWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<Integer,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      IntegerFieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<
      Integer,?,WkSerdeUnsignedByteWriter,WkSerdeUnsignedByte> defintionCore) {
    this.writingCore = new WkSerdeDtreeNumberMsgWriterCoreSimplified<>(
                                index,
                                IntegerFieldCore,
                                defintionCore,
                                this);
  }

  @Override
  public WkSerdeUnsignedByte definition() {
    return this.writingCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.writingCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Integer>> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.writingCore.parentField();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?,?,?>> subfields() {
    return this.writingCore.subfields();
  }

  @Override
  public String name() {
    return this.writingCore.name();
  }

  @Override
  public Integer serializable() {
    return this.writingCore.serializable();
  }

  @Override
  public String toString() {
    return this.writingCore.toString();
  }

}
