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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArray;

public class WkSerdeStringVariableBytesReader
    implements WkSerdeStringFromBytesReader<
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<String>,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArrayReader>
{

  final WkSerdeStringFromBytesReaderCoreSimplified<
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeStringVariableBytesReader,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray> operationCore;

  WkSerdeStringVariableBytesReader(
    int index,
    WkSerdeDtreeOperationSettingsVariableLength settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<
      String,?,WkSerdeStringVariableBytes,?,?,?> deserializingfieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      WkSerdeDtreeOperationSettingsVariableLength,WkSerdeStringVariableBytesReader,
      WkSerdeStringVariableBytes,?,?,?,WkSerdeDtreeOperationSettingsVariableLength,
      WkSerdeDtreeVariableSizeByteArrayReader,WkSerdeDtreeVariableSizeByteArray,?,?,?,?,?> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesReaderCoreSimplified<
                                WkSerdeDtreeOperationSettingsVariableLength,
                                WkSerdeStringVariableBytesReader,
                                WkSerdeStringVariableBytes,
                                WkSerdeDtreeOperationSettingsVariableLength,
                                WkSerdeDtreeVariableSizeByteArrayReader,
                                WkSerdeDtreeVariableSizeByteArray>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSerdeStringVariableBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettingsVariableLength settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgInputField<String, WkSerdeStringVariableBytes, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
