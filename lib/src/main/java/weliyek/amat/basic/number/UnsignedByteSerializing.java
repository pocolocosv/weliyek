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
package weliyek.amat.basic.number;

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;

public final class UnsignedByteSerializing
    implements NumberSerializing<
                        Integer,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        UnsignedByte>
{

  final SimpleNumberSerializerWritingCore<
                        Integer,
                        UnsignedByteSerializing,
                        UnsignedByte> writingCore;

  UnsignedByteSerializing(
    int index,
    Integer serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<Integer,?,UnsignedByte,?,?,?> writingfieldCore,
    SimplifiedNumberSerializerCore<
      Integer,?,UnsignedByteSerializing,UnsignedByte> defintionCore) {
    this.writingCore = new SimpleNumberSerializerWritingCore<>(
                                index,
                                serializable,
                                settings,
                                parentBytestream,
                                writingfieldCore,
                                defintionCore,
                                this);
  }

  @Override
  public UnsignedByte definition() {
    return this.writingCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.writingCore.settings();
  }

  @Override
  public SerializingRuntime<OutputBytestream> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public SerializingField<Integer, UnsignedByte, ?> packetField() {
    return this.writingCore.packetField();
  }

  @Override
  public List<SerializingSubfieldHandler<?,?,?>> subfields() {
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
