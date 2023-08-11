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
package weliyek.serialization;

import java.util.Objects;
import java.util.Optional;

import weliyek.Initializable;
import weliyek.Resetable;

public abstract class WkSzPacketSubfieldCore<
                        S extends WkSzOperationSettings,
                        TD extends WkSzDefinition<?,?>,
                        NC extends WkSzSubcomponentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        K extends WkSzPacketField<?,?,?>,
                        KC extends WkSzPacketFieldCore<?,?,?,?,?,?,? extends K,?,?>,
                        J extends WkSzPacketSubfield<?>,
                        AOC extends WkSzPacketOperationCore<?,?,?,?,AD,?,?,?,?,?,?>,
                        AD extends WkSzDefinition<?,?>>
    implements WkSzPacketSubfield<K>,
               Initializable,
               Resetable
{

  private NC protocolSubfieldCore = null;
  private AOC parentOperationCore = null;
  private KC packetFieldCore = null;
  private Optional<K> packetField = Optional.empty();
  /** A field can be optional, if is so, then a test will be done and its result
   * stored in this variable indicating that the field is enabled or not. The presence of
   * a value (i.e. non-null) also indicates that the field has been activated.
   */
  private Boolean packetIsEnabled;

  protected WkSzPacketSubfieldCore() {
    reset();
  }

  public abstract J asSubfield();

  public void initiliaze(
    NC protocolSubfieldCore,
    AOC parentOperationCore) {
    this.protocolSubfieldCore = Objects.requireNonNull(protocolSubfieldCore);
    this.parentOperationCore = Objects.requireNonNull(parentOperationCore);
    onInitialization();
  }

  protected abstract void onInitialization();

  @Override
  public boolean isInitialized() {
    return null != this.protocolSubfieldCore;
  }

  @Override
  public boolean isAwaitingActivation() {
    return null == this.packetIsEnabled;
  }

  protected abstract KC newPacket();

  @Override
  public Optional<K> field() {
    return this.packetField;
  }

  public KC fieldCore() {
    return this.packetFieldCore;
  }

  public void activateField() {
    if (isUnitialized()) {
      throw new IllegalStateException();
    }
    if (isFieldOptional()) {
      /*
      AO parentOperation = parentOperationCore().body();
      Predicate<? super AO> tester = this.protocolSubfieldCore.optionalityTest().get();
      this.packetIsEnabled = tester.test(parentOperation);
      */
      this.packetIsEnabled = testIfOptionalFieldIsToBeEnabled();
    } else {
      this.packetIsEnabled = true;
    }
    this.packetFieldCore = newPacket();
    this.packetFieldCore.initialize(packetIsEnabled);
    this.packetField = Optional.of(this.packetFieldCore.asPacket());
  }

  protected abstract boolean isFieldOptional();

  protected abstract boolean testIfOptionalFieldIsToBeEnabled();

  @Override
  public void reset() {
    onReset();
    this.protocolSubfieldCore = null;
    this.parentOperationCore = null;
    this.packetField = Optional.empty();
    this.packetIsEnabled = null;
  }

  protected abstract void onReset();

  public final Optional<WkSzPacketOperation<?,?,?,?,?>> processBytestream() {
    return this.packetFieldCore.processSingleStepBytestream();
  }

  protected abstract TD definition();

  protected abstract WkSzStructComponent<? extends TD> protocolField();

  public abstract WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> protocolFieldCore();

  public AOC parentOperationCore() {
    return this.parentOperationCore;
  }

  public NC subcomponentHandlerCore() {
    return this.protocolSubfieldCore;
  }

}
