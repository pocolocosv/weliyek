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

import java.util.Optional;

import weliyek.amat.bitcoin.protocol.message.MessageUnpackedBody;
import weliyek.ketza.util.ByteSequence;
import weliyek.ketza.util.ByteSequenceWrapper;

public abstract  class BitcoinMsgRejectKernelAbstract
                        <XB extends MessageUnpackedBody<?,?,?>,
                         XK extends BitcoinMsgRejectKernelAbstract<XB,XK>>
        extends PayloadCoreKernelAbstract<XB, XK>
        implements BitcoinMsgReject
{

    private String message;
    private byte ccode;
    private String reason;
    private ByteSequence data;

    BitcoinMsgRejectKernelAbstract(PayloadCoreKernelCommissioner<XB, XK> c) {
        super(c);
    }

    @Override
    public boolean isReject() {
        return true;
    }

    @Override
    public String message() {
        return this.message;
    }

    void setMessageString(String str) {
        if (null == str) {
            throw new IllegalArgumentException();
        }
        this.message = str;
    }

    @Override
    public Optional<BitcoinCommandName> messageAsCommand() {
        return BitcoinCommandName.fromString(message);
    }

    @Override
    public byte ccode() {
        return this.ccode;
    }

    void setCCodeValue(CCodeName ccode) {
        if (null == ccode) {
            throw new IllegalArgumentException();
        }
        this.ccode = ccode.value;
    }

    void setCCodeValue(byte val) {
        this.ccode = val;
    }

    @Override
    public String reason() {
        return this.reason;
    }

    void setReasonString(String str) {
        if (null == str) {
            throw new IllegalArgumentException();
        }
        this.reason = str;
    }

    @Override
    public ByteSequence data() {
        return this.data;
    }

    void setDataBytes(ByteSequence data) {
        if (null == data) {
            throw new IllegalArgumentException();
        }
        this.data = data;
    }

    @Override
    protected void onCommonPayloadKernelCommissionSteps() {
        resetFields();
    }

    @Override
    protected void onPayloadKernelDecommission() {
        resetFields();
    }

    private void resetFields() {
        this.message = "";
        this.ccode = 0;
        this.reason = "";
        this.data = ByteSequenceWrapper.EMPTY;
    }

}
