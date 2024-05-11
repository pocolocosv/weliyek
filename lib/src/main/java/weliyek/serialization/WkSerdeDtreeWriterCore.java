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

public final class WkSerdeDtreeWriterCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,?,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>>
    extends WkSerdeDtreeMsgOutputFieldCore<
                        T, YS, YO, YD, AYBC,
                        WkSerdeDtreeMsgWriterCore<?,?,?,?,?,?,?,?,?,?>,
                        WkSerdeDtreeWriter<T, YD, YO>,
                        WkSerdeDtreeStructCore<
                          T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>>
    implements WkSerdeDtreeWriter<T, YD, YO>
{

  static public class WritingParameters<
                        Y,
                        S extends WkSerdeDtreeOperationSettings,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>>
  {

    final Y serializable;
    final S settings;
    final AYB precursorBytestream;

    public WritingParameters(Y serializable, S settings, AYB precursorBytestream) {
      this.serializable = Objects.requireNonNull(serializable);
      this.settings = Objects.requireNonNull(settings);
      this.precursorBytestream = Objects.requireNonNull(precursorBytestream);
    }

  }

  private final WkSerdeDtreeWriterCore.WritingParameters<T,YS,AYBC> parameters;
  private Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> previousOpResult = Optional.empty();

  public WkSerdeDtreeWriterCore(
    WkSerdeDtreeStructCore<
      T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> structFieldCore,
    WkSerdeDtreeWriterCore.WritingParameters<T,YS,AYBC> parameters) {
    super(structFieldCore, true);
    this.parameters = parameters;
  }

  @Override
  public void processBytestream() {
    this.previousOpResult = super.processSingleStepBytestream();
  }

  @Override
  public Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> previousProcessingSteapResult() {
    return this.previousOpResult;
  }

  @Override
  protected YS newSettings(int index) {
    return this.parameters.settings;
  }

  @Override
  protected AYBC parentBytestream() {
    return this.parameters.precursorBytestream;
  }

  @Override
  protected T serializable(int index) {
    return this.parameters.serializable;
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return 1;
  }

  @Override
  protected void onPacketFieldInitialization() {
    // TODO Nothing to do?
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO Nothing to do?
  }

  @Override
  protected WkSerdeDtreeMsgWriterCore<?,?,?,?,?,?,?,?,?,?>
  parentOperationCore() {
    return null;
  }

  public WkSerdeDtreeMsgWriter<?, ?, ?, ?, ?> parentOperation() {
    return null;
  }

  @Override
  public WkSerdeDtreeWriter<T, YD, YO> asPacket() {
    return this;
  }

  @Override
  protected boolean isOptional() {
    return false;
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

}
