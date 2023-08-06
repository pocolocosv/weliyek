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
package weliyek.amat.basic.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.sequence.OptionalLengthSettings;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.VariableSizeByteArray;
import weliyek.ketza.util.array.VariableSizeByteArraySerializing;
import weliyek.serialization.bytestream.OutputBytestream;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class StringWithVariableLengthBytesSerializing
    implements WkSzStringFromBytesWriter<
                        OptionalLengthSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        StringWithVariableLengthBytes,
                        VariableSizeByteArray,
                        VariableSizeByteArraySerializing>
{

  final SimpleStringFromBytesWritingCore<
                        OptionalLengthSettings,
                        StringWithVariableLengthBytesSerializing,
                        StringWithVariableLengthBytes,
                        OperationSettings,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray> operationCore;

  StringWithVariableLengthBytesSerializing(
    int index,
    String serializable,
    OptionalLengthSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      String,?,StringWithVariableLengthBytes,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,OptionalLengthSettings,StringWithVariableLengthBytesSerializing,
      StringWithVariableLengthBytes,?,?,?,OperationSettings,
      VariableSizeByteArraySerializing,VariableSizeByteArray,?,
      ? extends StringWithVariableLengthBytes> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<
                                OptionalLengthSettings,
                                StringWithVariableLengthBytesSerializing,
                                StringWithVariableLengthBytes,
                                OperationSettings,
                                VariableSizeByteArraySerializing,
                                VariableSizeByteArray>(
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
  WkSzPacketWriterSubfield<ByteArrayWrapper, VariableSizeByteArray, VariableSizeByteArraySerializing>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithVariableLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public OptionalLengthSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public SerializingRuntime<OutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<String, StringWithVariableLengthBytes, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
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
  WkSzPacketWriterSubfield<ByteArrayWrapper, VariableSizeByteArray, VariableSizeByteArraySerializing>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
