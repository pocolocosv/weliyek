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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import weliyek.ketza.util.ByteSequence;

public interface BitcoinMsgReject extends BitcoinMsgPayload
{

    enum CCodeName {
        REJECT_MALFORMED(0x01),
        REJECT_INVALID(0x10),
        REJECT_OBSOLETE(0x11),
        REJECT_DUPLICATE(0x12),
        REJECT_NONSTANDARD(0x40),
        REJECT_INSUFFICIENTFEE(0x42),
        REJECT_CHECKPOINT(0x43);

        public final byte value;

        private static final Map<Byte, CCodeName> NAMES_BY_VALUE;

        static {
            NAMES_BY_VALUE = new HashMap<>();
            NAMES_BY_VALUE.put(REJECT_MALFORMED.value, REJECT_MALFORMED);
            NAMES_BY_VALUE.put(REJECT_INVALID.value, REJECT_INVALID);
            NAMES_BY_VALUE.put(REJECT_OBSOLETE.value, REJECT_OBSOLETE);
            NAMES_BY_VALUE.put(REJECT_DUPLICATE.value, REJECT_DUPLICATE);
            NAMES_BY_VALUE.put(REJECT_NONSTANDARD.value, REJECT_NONSTANDARD);
            NAMES_BY_VALUE.put(REJECT_INSUFFICIENTFEE.value, REJECT_INSUFFICIENTFEE);
            NAMES_BY_VALUE.put(REJECT_CHECKPOINT.value, REJECT_CHECKPOINT);
        }

        private CCodeName(int code) {
            this.value = (byte) code;
        }

        public static Optional<CCodeName> from(byte b) {
            final CCodeName ccode = NAMES_BY_VALUE.get(b);
            return Optional.ofNullable(ccode);
        }
    }

    String message();

    Optional<WkBitcoinCommandName> messageAsCommand();

    byte ccode();

    String reason();

    ByteSequence data();

}
