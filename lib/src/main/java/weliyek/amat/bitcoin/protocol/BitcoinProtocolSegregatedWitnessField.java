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
package weliyek.amat.bitcoin.protocol;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldValue;

public class BitcoinProtocolSegregatedWitnessField<W extends BitcoinProtocolSegregatedWitness<?>>
        extends AmatProtocolMessageFieldValue<W,
                                              AmatNamespace>
{

    public final AmatNamespace segwitListSizeNamespace;
    public final AmatNamespace segwitListElementNamespace;
    public final AmatNamespace segwitListElementScriptNamespace;
    public final AmatNamespace segwitListElementScriptSizeNamespace;
    public final AmatNamespace segwitListElementScriptOpNamespace;
    public final AmatNamespace segwitListElementScriptOpDataSizeNamespace;
    public final AmatNamespace segwitListElementScriptOpDataBodyNamespace;

    public BitcoinProtocolSegregatedWitnessField(AmatNamespace namespace) {
        super(namespace.newChildNamespace(BitcoinProtocolName.SEGWIT_LIST));
        segwitListSizeNamespace = namespace().newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_SIZE);
        segwitListElementNamespace = namespace().newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_ELEMENT);
        segwitListElementScriptNamespace = segwitListElementNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT);
        segwitListElementScriptSizeNamespace = segwitListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_SIZE);
        segwitListElementScriptOpNamespace = segwitListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_CODE);
        segwitListElementScriptOpDataSizeNamespace = segwitListElementScriptOpNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_SIZE);
        segwitListElementScriptOpDataBodyNamespace = segwitListElementScriptOpNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_BODY);
     }

}
