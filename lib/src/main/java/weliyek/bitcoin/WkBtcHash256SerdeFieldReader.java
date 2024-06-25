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
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayReader;

public class WkBtcHash256SerdeFieldReader
    implements WkSerdeDtreeAggregatorMsgReader<
                        WkBtcHash256,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkBtcHash256>,
                        WkBtcHash256SerdeField>
{

  static WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        WkBtcHash256,
                        WkSerdeDtreeOperationSettings,
                        WkBtcHash256SerdeField,
                        WkBtcHash256SerdeFieldReader>
  newCore(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?, ?, ?, ?, ?, ?, ?, ?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcHash256, WkSerdeDtreeOperationSettings, WkBtcHash256SerdeField, WkBtcHash256SerdeFieldReader, ?, ?, ?, ? extends WkBtcHash256SerdeField>
      definitionCore) {
    return new WkBtcHash256SerdeFieldReader(index, settings, parentBytestream, readerFieldCore, definitionCore).readerCore;
  }

  final WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        WkBtcHash256,
                        WkSerdeDtreeOperationSettings,
                        WkBtcHash256SerdeField,
                        WkBtcHash256SerdeFieldReader> readerCore;

  private WkBtcHash256SerdeFieldReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?, ?, ?, ?, ?, ?, ?, ?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcHash256, WkSerdeDtreeOperationSettings, WkBtcHash256SerdeField, WkBtcHash256SerdeFieldReader, ?, ?, ?, ? extends WkBtcHash256SerdeField>
      definitionCore) {
    this.readerCore = new WkSerdeDtreeAggregatorMsgReaderCoreSimplified<WkBtcHash256, WkSerdeDtreeOperationSettings, WkBtcHash256SerdeField, WkBtcHash256SerdeFieldReader>(
                              index,
                              settings,
                              parentBytestream,
                              readerFieldCore,
                              definitionCore,
                              this);
  }

  public Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>>
  bytes() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().bytes).asPacket());
  }

  @Override
  public WkBtcHash256SerdeField definition() {
    return this.readerCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.readerCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.readerCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkBtcHash256>> result() {
    return this.readerCore.result();
  }

  @Override
  public int index() {
    return this.readerCore.index();
  }

  @Override
  public String name() {
    return this.readerCore.name();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.readerCore.subfields();
  }

  @Override
  public WkSerdeDtreeMsgInputField<?, ?, ?> parentField() {
    return this.readerCore.parentField();
  }

}
