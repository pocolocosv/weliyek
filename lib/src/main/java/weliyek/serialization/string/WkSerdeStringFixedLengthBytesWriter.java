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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;

public class WkSerdeStringFixedLengthBytesWriter
    implements WkSerdeStringFromBytesWriter<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<String>,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter>
{

  final WkSerdeStringFromBytesWriterCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray> operationCore;

  WkSerdeStringFixedLengthBytesWriter(
    int index,
    String serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<
      String,?,WkSerdeStringFixedLengthBytes,?,?,?> serializingfieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      ?,?,?,WkSerdeDtreeOperationSettings,WkSerdeStringFixedLengthBytesWriter,
      WkSerdeStringFixedLengthBytes,?,?,?,WkSerdeDtreeOperationSettings,
      WkSerdeDtreeFixedSizeByteArrayWriter,WkSerdeDtreeFixedSizeByteArray,?,
      ? extends WkSerdeStringFixedLengthBytes> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesWriterCoreSimplified<WkSerdeDtreeOperationSettings,
        WkSerdeStringFixedLengthBytesWriter,
        WkSerdeStringFixedLengthBytes,
        WkSerdeDtreeOperationSettings,
        WkSerdeDtreeFixedSizeByteArrayWriter,
        WkSerdeDtreeFixedSizeByteArray>(
                                  index,
                                  serializable,
                                  settings,
                                  parentBytestream,
                                  serializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataOutputComponent<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter>>
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
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
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
  public WkSerdeDtreeNodeDataOutputComponent<String, WkSerdeStringFixedLengthBytes, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public String serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataOutputComponent<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
