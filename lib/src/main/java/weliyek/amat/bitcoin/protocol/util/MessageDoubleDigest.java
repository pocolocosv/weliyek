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
package weliyek.amat.bitcoin.protocol.util;

import java.security.MessageDigest;

public class MessageDoubleDigest extends MessageDigest implements Cloneable
{

    private final MessageDigest hasher;

    public MessageDoubleDigest(MessageDigest hasher) {
        super(hasher.getAlgorithm());
        this.hasher = hasher;
    }

    @Override
    protected void engineUpdate(byte input) {
        this.hasher.update(input);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        this.hasher.update(input, offset, len);
    }

    @Override
    protected byte[] engineDigest() {
        final byte[] hash = this.hasher.digest();
        return this.hasher.digest(hash);
    }

    @Override
    protected void engineReset() {
        this.hasher.reset();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final MessageDigest hasherClone = (MessageDigest) this.hasher.clone();
        final MessageDoubleDigest mdd = new MessageDoubleDigest(hasherClone);
        return mdd;
    }

}
