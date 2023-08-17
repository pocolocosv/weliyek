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

public abstract class PayloadCoreKernelAbstract<K extends PayloadCoreKernelAbstract<?>>
        implements PayloadCoreKernel<K>
{

    private MessageUnpackedCore<?, ?, ?> ownerCore;

    @Override
    public void commissionWithOwnerCore(MessageUnpackedCore<?, ?, ?> ownerCore) {
        setOwnerCoreOnCommission(ownerCore);
    }

    protected void setOwnerCoreOnCommission(MessageUnpackedCore<?, ?, ?> ownerCore) {
        this.ownerCore = ownerCore;
        onOwnerSetting();
    }

    @Override
    public MessageUnpackedCore<?, ?, ?> ownerCore() {
        return ownerCore;
    }

    protected abstract void onOwnerSetting();

    @Override
    public void finilizeCommission() {
        // Nothing to do.
    }

    @Override
    public boolean isCommissioned() {
        return null != ownerCore;
    }

    protected abstract void onPayloadDecommission();

    @Override
    public void decommission() {
        onPayloadDecommission();
        this.ownerCore = null;
    }

    @Override
    public BitcoinMessageMagic magic() {
        return ownerCore.magic();
    }

    @Override
    public WkBitcoinCommand command() {
        return ownerCore.command();
    }

    @Override
    public boolean isVersion() {
        return false;
    }

    @Override
    public boolean isAddr() {
        return false;
    }

    @Override
    public boolean isGetData() {
        return false;
    }

    @Override
    public boolean isTx() {
        return false;
    }

    @Override
    public boolean isHeaders() {
        return false;
    }

    @Override
    public boolean isNotFound() {
        return false;
    }

    @Override
    public boolean isGetBlocks() {
        return false;
    }

    @Override
    public boolean isReject() {
        return false;
    }

}
