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
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;

public final class SignedLittleEndianShortDeserializing
        implements NumberDeserializing<
                        Short,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<Short>,
                        SignedLittleEndianShort>
{

    final SimpleNumberReadingCore<
                        Short,
                        SignedLittleEndianShortDeserializing,
                        SignedLittleEndianShort> operationCore;

    SignedLittleEndianShortDeserializing(
      int index,
      OperationSettings settings,
      InputBytestreamGeneralBase<?> parentBytestream,
      DeserializingFieldCore<
        Short,?,SignedLittleEndianShort,?,?,?> deserializingpacketCore,
      SimplifiedNumberSerializerCore<
        Short,SignedLittleEndianShortDeserializing,?,SignedLittleEndianShort> definitionCore) {
      this.operationCore = new SimpleNumberReadingCore<>(
                                    index, settings, parentBytestream,
                                    deserializingpacketCore,
                                    definitionCore,
                                    this);
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
    public Optional<DeserializingResult<Short>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public DeserializingField<Short, SignedLittleEndianShort, ?> packetField() {
      return this.operationCore.packet();
    }

    @Override
    public final List<DeserializingSubfieldHandler<?,?,?>> subfields() {
      return this.operationCore.subfields();
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
