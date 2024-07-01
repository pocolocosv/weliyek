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

public class WkSerdeStringFixedLengthBytesReader
    implements WkSerdeStringFromBytesReader<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<String>,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader>
{

  final WkSerdeStringFromBytesReaderCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringFixedLengthBytesReader,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSerdeDtreeFixedSizeByteArray> operationCore;

  WkSerdeStringFixedLengthBytesReader(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamInputBase<?>,?,?,?>
      readerFieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      WkSerdeDtreeOperationSettings, WkSerdeStringFixedLengthBytesReader,
      WkSerdeStringFixedLengthBytes, ?, ?, ?,
      WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArrayReader,
      WkSerdeDtreeFixedSizeByteArray, ?, ?, ?,
      ? extends WkSerdeDtreeFixedSizeByteArray,
      ? extends WkSerdeStringFixedLengthBytes> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesReaderCoreSimplified<
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytesReader,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeDtreeFixedSizeByteArrayReader,
                                WkSerdeDtreeFixedSizeByteArray>(
                                  index,
                                  readerFieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSerdeStringFixedLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
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
  public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
