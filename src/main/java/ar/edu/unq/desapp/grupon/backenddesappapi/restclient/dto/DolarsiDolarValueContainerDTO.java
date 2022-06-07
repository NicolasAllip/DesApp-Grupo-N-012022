package ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto;

import java.util.Objects;

public class DolarsiDolarValueContainerDTO {
    private DolarsiDolarValueDTO casa;

    public DolarsiDolarValueDTO getCasa() {
        return casa;
    }

    public void setCasa(DolarsiDolarValueDTO casa) {
        this.casa = casa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DolarsiDolarValueContainerDTO that = (DolarsiDolarValueContainerDTO) o;
        return Objects.equals(casa, that.casa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(casa);
    }

    public static DolarsiDolarValueContainerDTOBuilder builder() {
        return new DolarsiDolarValueContainerDTOBuilder();
    }

    public static final class DolarsiDolarValueContainerDTOBuilder {
        private DolarsiDolarValueContainerDTO dolarsiDolarValueContainerDTO;

        public DolarsiDolarValueContainerDTOBuilder casa(DolarsiDolarValueDTO casa) {
            dolarsiDolarValueContainerDTO.setCasa(casa);
            return this;
        }

        public DolarsiDolarValueContainerDTO build() {
            return dolarsiDolarValueContainerDTO;
        }
    }
}
