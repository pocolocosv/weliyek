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

import weliyek.serialization.filter.Filter;
import weliyek.serialization.filter.FilterResults;

public final class WkSzInputPacketCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,? extends WkSzReadingResult<T>,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>>
    extends WkSzPacketReaderFieldCore<
                        T, XS, XD, XO, AXBC,
                        WkSzAggregatorReader<?,?,? extends WkSzReadingRuntime<?>,?,?>>
    implements WkSzInputPacket<T, XD, XO>
{

  public static class ReadingPacketParameters<
                        S extends WkSzOperationSettings,
                        ABC extends WkSzInputBytestreamBase<?>>
  {

    final S settings;
    final ABC bytestream;
    final FilterResults filterResults;

    public ReadingPacketParameters(S settings, ABC bytestream, Filter filter) {
      this.settings = Objects.requireNonNull(settings);
      this.bytestream = Objects.requireNonNull(bytestream);
      this.filterResults = Objects.requireNonNull(filter).buildNewResults();
    }

  }

  private final ReadingPacketParameters<XS,AXBC> parameters;
  private Optional<WkSzPacketOperation<?,?,?,?,?>> previousOpResult = Optional.empty();

  public WkSzInputPacketCore(
    WkSzStructComponentCoreBase<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> protocolHandler,
    ReadingPacketParameters<XS,AXBC> parameters) {
    super(1, protocolHandler);
    this.parameters = parameters;
  }

  @Override
public void processBytestream() {
    this.previousOpResult = super.processSingleStepBytestream();
  }

  @Override
public Optional<WkSzPacketOperation<?,?,?,?,?>> previousProcessingSteapResult() {
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
  public FilterResults filterResults() {
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
  protected WkSzAggregatorReaderCore<?,?,?,AXBC,?,?,?,?,WkSzAggregatorReader<?,?,? extends WkSzReadingRuntime<?>,?,?>,?,?,?>
  parentOperationCore() {
    return null;
  }

  /* There's no need to return ReadingPacket type here since it's not needed by those
   * using this method. If we try to return said type here, then we need to change
   * handling of the generic parameter in parent types which would increase complexity.
   */
  @Override
  public WkSzInputPacket<T, XD, XO> asPacket() {
    return this;
  }

}
