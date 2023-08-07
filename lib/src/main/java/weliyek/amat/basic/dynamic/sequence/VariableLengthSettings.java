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
package weliyek.amat.basic.dynamic.sequence;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.serialization.WkSzPacketOperation;

public interface VariableLengthSettings extends OperationSettings
{

  static <AO extends WkSzPacketOperation<VariableLengthSettings,?,?,?,?>>
  OperationSubsegmentSettingsFactory<AO, VariableLengthSettings>
  builderWithLength(int length) {
    return new OperationSubsegmentSettingsFactory<AO, VariableLengthSettings>() {
      @Override
      public VariableLengthSettings newSettings(int i, AO parentOperation) {
        return VariableLengthSettings.withLength(length);
      }
    };
  }

  static VariableLengthSettings withLength(int length) {
    return new VariableLengthSettings() {

      @Override
      public int getRequestedLength() {
        return length;
      }
    };
  }

  int getRequestedLength();

}
