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

public final class SignedLittleEndianShortSerializing
        implements NumberSerializing<
                        Short,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        SignedLittleEndianShort>
{

  final SimpleNumberSerializerWritingCore<
                        Short,
                        SignedLittleEndianShortSerializing,
                        SignedLittleEndianShort> operationCore;

  SignedLittleEndianShortSerializing(
    int index,
    Short serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<
      Short,?,SignedLittleEndianShort,?,?,?> serializingfieldCore,
    SimplifiedNumberSerializerCore<
      Short,?,SignedLittleEndianShortSerializing,SignedLittleEndianShort> definitionCore) {
    operationCore = new SimpleNumberSerializerWritingCore<>(
                            index,
                            serializable,
                            settings,
                            parentBytestream,
                            serializingfieldCore,
                            definitionCore,
                            this);
  }

  @Override
  public OperationSettings settings() {
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
  public Short serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<SerializingSubfieldHandler<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public SerializingField<Short, SignedLittleEndianShort, ?> packetField() {
    return this.operationCore.packetFieldCore().asPacket();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public String toString() {
    return this.operationCore.toString();
  }

  @Override
  public SignedLittleEndianShort definition() {
    return this.operationCore.definition();
  }

}
