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

import weliyek.serialization.filter.WkSrlzFilter;
import weliyek.serialization.filter.WkSrlzFilterResults;

public final class WkSerdeDtreeReaderCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,?,XD>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>>
    extends WkSerdeDtreeMsgInputFieldCore<
                        T, XS, XO, XD, AXBC,
                        WkSerdeDtreeMsgReaderCore<?,?,?,?,?,?,?,?,?,?>,
                        WkSerdeDtreeReader<T, XD, XO>,
                        WkSerdeDtreeStructCore<
                          T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>>
    implements WkSerdeDtreeReader<T, XD, XO>
{

  public static class ReadingPacketParameters<
                        S extends WkSerdeDtreeOperationSettings,
                        ABC extends WkSerdeDtreeBytestreamInputBase<?>>
  {

    final S settings;
    final ABC bytestream;
    final WkSrlzFilterResults filterResults;

    public ReadingPacketParameters(S settings, ABC bytestream, WkSrlzFilter filter) {
      this.settings = Objects.requireNonNull(settings);
      this.bytestream = Objects.requireNonNull(bytestream);
      this.filterResults = Objects.requireNonNull(filter).buildNewResults();
    }

  }

  private final ReadingPacketParameters<XS,AXBC> parameters;
  private Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> previousOpResult = Optional.empty();

  public WkSerdeDtreeReaderCore(
    WkSerdeDtreeStructCore<
      T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> structFieldCore,
    ReadingPacketParameters<XS,AXBC> parameters) {
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

  /*
  @Override
  public boolean isRequiredByProtocol() {
    return this.filterResults().filter().isEmpty();
  }

  @Override
  public boolean isOptionallyEnabledByTest() {
    return false;
  }
  */

  @Override
  public WkSrlzFilterResults filterResults() {
    return this.parameters.filterResults;
  }

  @Override
  protected void onPacketInputFieldHandlerInitialization() {
    // TODO: Nothing to do?
  }

  @Override
  protected XS newSettings(int index) {
    return this.parameters.settings;
  }

  @Override
  protected AXBC parentBytestream() {
    return this.parameters.bytestream;
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return 1;
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO: Nothing to do?
  }

  @Override
  protected void onDoneProcessing() {
    // TODO: Nothing to do?
  }

  @Override
  protected WkSerdeDtreeMsgReaderCore<?,?,?,?,?,?,?,?,?,?>
  parentOperationCore() {
    return null;
  }

  @Override
  public WkSerdeDtreeReader<T, XD, XO> asPacket() {
    return this;
  }

  @Override
  protected boolean isOptional() {
    return false;
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    throw new UnsupportedOperationException();
  }

}
