/*
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
package weliyek.bitcoin;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;

public class WkBtcNetNodeServicesSerdeField
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcNetNodeServices>
{

  public static WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeField,
                        WkBtcNetNodeServicesSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeField,
                        WkBtcNetNodeServicesSerdeFieldWriter,
                        WkBtcNetNodeServicesSerdeField>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkBtcNetNodeServicesSerdeField(componentCore).definitionCore;
  }

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBtcNetNodeServices,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetNodeServicesSerdeField,
                    WkBtcNetNodeServicesSerdeFieldReader,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetNodeServicesSerdeField,
                    WkBtcNetNodeServicesSerdeFieldWriter,
                    WkBtcNetNodeServicesSerdeField> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                    Long,
                    WkBtcNetNodeServices,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeSignedLittleEndianLong,
                    WkSerdeSignedLittleEndianLongReader,
                    WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                    WkBtcNetNodeServicesSerdeFieldReader,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeSignedLittleEndianLong,
                    WkSerdeSignedLittleEndianLongWriter,
                    WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                    WkBtcNetNodeServicesSerdeFieldWriter,
                    WkSerdeSignedLittleEndianLong> int64le;

  public WkBtcNetNodeServicesSerdeField(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
        WkBtcNetNodeServices,
        WkSerdeDtreeOperationSettings,
        WkBtcNetNodeServicesSerdeField,
        WkBtcNetNodeServicesSerdeFieldReader,
        WkSerdeDtreeOperationSettings,
        WkBtcNetNodeServicesSerdeField,
        WkBtcNetNodeServicesSerdeFieldWriter,
        WkBtcNetNodeServicesSerdeField>(
            (i,xs,axb,xpc,dc) -> new WkBtcNetNodeServicesSerdeFieldReader(i,xs,axb,xpc,dc).inputCore,
            (i,y,ys,ayb,ypc,dc) -> new WkBtcNetNodeServicesSerdeFieldWriter(i,y,ys,ayb,ypc,dc).outputCore,
            componentCore,
            (ic) -> {},
            (ic) -> WkBtcNetNodeServices.fromLong(ic.body().int64le().get().firstOperation().get().result().get().serializable().get().longValue()),
            (ic) -> {},
            (oc) -> {},
            this,
            WkBtcNetNodeServices.class);
    this.int64le = this.definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter, WkSerdeSignedLittleEndianLong>
    addSubcomponent(
        "LONG",
        Optional.empty(), // rxEnablingTest
        WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
        WkSerdeDtreeOperationSettings::none,
        Optional.empty(), // txEnablingTest
        WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
        WkSerdeDtreeOperationSettings::none,
        (k,ao,i) -> Long.valueOf(ao.serializable().toLong()), //disaggregator,
        false,
        WkSerdeSignedLittleEndianLong::newCore);
  }

  @Override
  public Class<WkBtcNetNodeServices> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
