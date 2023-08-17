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

import weliyek.ketza.util.ByteSequence;

public class BitcoinMsgRejectRWKernel
        extends BitcoinMsgRejectKernelAbstract<BitcoinMsgRWBody, BitcoinMsgRejectRWKernel>
        implements BitcoinMsgRejectRW,
                   PayloadCoreKernelRW<BitcoinMsgRejectROKernel>
{

    BitcoinMsgRejectRWKernel(BitcoinMsgRejectRWKernelCommissioner c) {
        super(c);
    }

    @Override
    public void commissionWithRWArgs(BitcoinMsgRWBody body, MessageRWArgs args) {
        commonPayloadKernelCommissionSteps(body);
    }

    @Override
    public void commissionWithROKernel(BitcoinMsgRWBody body, BitcoinMsgRejectROKernel roKernel) {
        commonPayloadKernelCommissionSteps(body);
        setMessageString(roKernel.message());
        setCCodeValue(roKernel.ccode());
        setReason(roKernel.reason());
        setData(roKernel.data());
    }

    @Override
    public BitcoinMsgRejectRW setMessage(WkBitcoinCommandName cmd) {
        setMessageString(cmd.toString());
        return this;
    }

    @Override
    public BitcoinMsgRejectRW setCCode(CCodeName ccode) {
        setCCodeValue(ccode);
        return this;
    }

    @Override
    public BitcoinMsgRejectRW setReason(String str) {
        setReasonString(str);
        return this;
    }

    @Override
    public BitcoinMsgRejectRW setData(ByteSequence data) {
        setDataBytes(data);
        return this;
    }

    @Override
    protected BitcoinMsgRejectRWKernel getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgRejectRWKernel getThis() {
        return this;
    }

}
