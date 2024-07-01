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

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsOptionalLength;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArray;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayWriter;

public class WkSerdeStringVariableBytesWriter
    implements WkSerdeStringFromBytesWriter<
                        WkSerdeDtreeOperationSettingsOptionalLength,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<String>,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArrayWriter>
{

  final WkSerdeStringFromBytesWriterCoreSimplified<
                        WkSerdeDtreeOperationSettingsOptionalLength,
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray> operationCore;

  WkSerdeStringVariableBytesWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<String,WkSerdeDtreeOperationSettingsOptionalLength,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writerFieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<
      ?,?,?,WkSerdeDtreeOperationSettingsOptionalLength, WkSerdeStringVariableBytesWriter,
      WkSerdeStringVariableBytes, ?, ?, ?, WkSerdeDtreeOperationSettings,
      WkSerdeDtreeVariableSizeByteArrayWriter, WkSerdeDtreeVariableSizeByteArray,
      ? extends WkSerdeDtreeVariableSizeByteArray,
      ? extends WkSerdeStringVariableBytes> definitionCore) {
    this.operationCore = new WkSerdeStringFromBytesWriterCoreSimplified<
                                WkSerdeDtreeOperationSettingsOptionalLength,
                                WkSerdeStringVariableBytesWriter,
                                WkSerdeStringVariableBytes,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeDtreeVariableSizeByteArrayWriter,
                                WkSerdeDtreeVariableSizeByteArray>(
                                    index,
                                    writerFieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgOutputField<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayWriter>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSerdeStringVariableBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettingsOptionalLength settings() {
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
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
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
  Optional<WkSerdeDtreeMsgOutputField<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayWriter>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
