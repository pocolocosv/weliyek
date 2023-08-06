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
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;

public final class WkSzUnsignedBigEndianShortReader
        implements WkSzNumberReader<
                        Integer,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<Integer>,
                        WkSzUnsignedBigEndianShort>
{

  final WkSzSimplifiedNumberReaderCore<
                      Integer,
                      WkSzUnsignedBigEndianShortReader,
                      WkSzUnsignedBigEndianShort> operationCore;

  WkSzUnsignedBigEndianShortReader(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      Integer,?,WkSzUnsignedBigEndianShort,?,?,?> packetfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<
      Integer,WkSzUnsignedBigEndianShortReader,?,WkSzUnsignedBigEndianShort> definitionCore) {
    operationCore = new WkSzSimplifiedNumberReaderCore<>(
                            index, settings, parentBytestream, packetfieldCore, definitionCore, this);
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
  public Optional<DeserializingResult<Integer>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<Integer, WkSzUnsignedBigEndianShort, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public final List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
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
  public WkSzUnsignedBigEndianShort definition() {
    return this.operationCore.definition();
  }

}