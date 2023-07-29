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
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.FixedSizeByteArray;
import weliyek.ketza.util.array.FixedSizeByteArrayDeserializing;

public class StringWithFixedLengthBytesDeserializing
    implements StringFromBytesReading<
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<String>,
                        StringWithFixedLengthBytes,
                        FixedSizeByteArray,
                        FixedSizeByteArrayDeserializing>
{

  final SimplifiedStringFromBytesReadingCore<
                        OperationSettings,
                        StringWithFixedLengthBytesDeserializing,
                        StringWithFixedLengthBytes,
                        OperationSettings,
                        FixedSizeByteArrayDeserializing,
                        FixedSizeByteArray> operationCore;

  StringWithFixedLengthBytesDeserializing(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<
      String,?,StringWithFixedLengthBytes,?,?,?> deserializingfieldCore,
    SimplifiedStringFromBytesCore<
      OperationSettings,StringWithFixedLengthBytesDeserializing,
      StringWithFixedLengthBytes,?,?,?,OperationSettings,
      FixedSizeByteArrayDeserializing,FixedSizeByteArray,?,?,?,?,
      ? extends StringWithFixedLengthBytes> definitionCore) {
    this.operationCore = new SimplifiedStringFromBytesReadingCore<
                                OperationSettings,
                                StringWithFixedLengthBytesDeserializing,
                                StringWithFixedLengthBytes,
                                OperationSettings,
                                FixedSizeByteArrayDeserializing,
                                FixedSizeByteArray>(
                                  index,
                                  settings,
                                  parentBytestream,
                                  deserializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  DeserializingSubfieldHandler<ByteArrayWrapper, FixedSizeByteArray, FixedSizeByteArrayDeserializing>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithFixedLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public DeserializingRuntime<InputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public DeserializingField<String, StringWithFixedLengthBytes, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<DeserializingSubfieldHandler<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  DeserializingSubfieldHandler<ByteArrayWrapper, FixedSizeByteArray, FixedSizeByteArrayDeserializing>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
