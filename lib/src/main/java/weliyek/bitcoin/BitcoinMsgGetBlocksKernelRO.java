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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitcoinMsgGetBlocksKernelRO
        extends BitcoinMsgGetBlocksKernelAbstract<BitcoinMsgROBody, BitcoinMsgGetBlocksKernelRO>
        implements BitcoinMsgGetBlocksRO,
                   PayloadCoreKernelRO
{

    protected List<BitcoinHash> list = new ArrayList<>();

    protected List<BitcoinHash> unmodifiableList = Collections.unmodifiableList(list);

    public BitcoinMsgGetBlocksKernelRO(BitcoinMsgGetBlocksKernelROCommissioner c) {
        super(c);
    }

    @Override
    public void commission(BitcoinMsgROBody owner) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    protected List<BitcoinHash> modifiableHeaderHashList() {
        return list;
    }

    @Override
    protected List<BitcoinHash> publicHeaderHashList() {
        return unmodifiableList;
    }

    @Override
    protected BitcoinMsgGetBlocksKernelRO getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgGetBlocksKernelRO getThis() {
        return this;
    }

}