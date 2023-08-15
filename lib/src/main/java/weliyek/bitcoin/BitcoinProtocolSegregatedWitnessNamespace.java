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
package weliyek.bitcoin;

import weliyek.amat.base.namespace.AmatNamespace;

public class BitcoinProtocolSegregatedWitnessNamespace
        extends AmatNamespace
{

    public final AmatNamespace size;
    public final AmatNamespace element;
    public final BitcoinProtocolScriptNamespaces script;

    public BitcoinProtocolSegregatedWitnessNamespace(AmatNamespace parentNamespace) {
        super(parentNamespace, BitcoinProtocolName.SEGWIT_LIST, parentNamespace.factory());
        size = newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_SIZE);
        element = newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_ELEMENT);
        script = new BitcoinProtocolScriptNamespaces(element);
    }

}
