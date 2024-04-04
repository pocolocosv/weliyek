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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;

public class WkSerdeStringFixedLengthBytesWriter
    implements WkSerdeStringFromBytesWriter<
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<String>,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter>
{

  final WkSerdeStringFromBytesWriterCoreSimplified<
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSerdeStringFixedLengthBytes,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray> operationCore;

  WkSerdeStringFixedLengthBytesWriter(
    int index,
    String serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      String,?,WkSerdeStringFixedLengthBytes,?,?,?> serializingfieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      ?,?,?,WkSettingsSrlzPacketOperationData,WkSerdeStringFixedLengthBytesWriter,
      WkSerdeStringFixedLengthBytes,?,?,?,WkSettingsSrlzPacketOperationData,
      WkSerdeDtreeFixedSizeByteArrayWriter,WkSerdeDtreeFixedSizeByteArray,?,
      ? extends WkSerdeStringFixedLengthBytes> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesWriterCoreSimplified<WkSettingsSrlzPacketOperationData,
        WkSerdeStringFixedLengthBytesWriter,
        WkSerdeStringFixedLengthBytes,
        WkSettingsSrlzPacketOperationData,
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
  Optional<WkSrlzOutputPacketFieldFrameNode<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSerdeStringFixedLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSettingsSrlzPacketOperationData settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<String, WkSerdeStringFixedLengthBytes, ?> packetField() {
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
  Optional<WkSrlzOutputPacketFieldFrameNode<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
