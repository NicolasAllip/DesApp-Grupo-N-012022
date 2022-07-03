package ar.edu.unq.desapp.grupon.backenddesappapi.restclient.dto;

import java.util.Objects;

public class DolarsiDolarValueDTO {

    private String compra;
    private String venta;
    private String agencia;
    private String nombre;
    private String variacion;
    private String ventaCero;
    private String decimales;

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariacion() {
        return variacion;
    }

    public void setVariacion(String variacion) {
        this.variacion = variacion;
    }

    public String getVentaCero() {
        return ventaCero;
    }

    public void setVentaCero(String ventaCero) {
        this.ventaCero = ventaCero;
    }

    public String getDecimales() {
        return decimales;
    }

    public void setDecimales(String decimales) {
        this.decimales = decimales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DolarsiDolarValueDTO that = (DolarsiDolarValueDTO) o;
        return Objects.equals(compra, that.compra) && Objects.equals(venta, that.venta) && Objects.equals(agencia, that.agencia) && Objects.equals(nombre, that.nombre) && Objects.equals(variacion, that.variacion) && Objects.equals(ventaCero, that.ventaCero) && Objects.equals(decimales, that.decimales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compra, venta, agencia, nombre, variacion, ventaCero, decimales);
    }

    public static DolarsiDolarValueDTOBuilder builder() {
        return new DolarsiDolarValueDTOBuilder();
    }

    public static final class DolarsiDolarValueDTOBuilder {
        private DolarsiDolarValueDTO dolarsiDolarValueDTO;

        public DolarsiDolarValueDTOBuilder compra(String compra) {
            dolarsiDolarValueDTO.setCompra(compra);
            return this;
        }

        public DolarsiDolarValueDTOBuilder venta(String venta) {
            dolarsiDolarValueDTO.setVenta(venta);
            return this;
        }

        public DolarsiDolarValueDTOBuilder agencia(String agencia) {
            dolarsiDolarValueDTO.setAgencia(agencia);
            return this;
        }

        public DolarsiDolarValueDTOBuilder nombre(String nombre) {
            dolarsiDolarValueDTO.setNombre(nombre);
            return this;
        }

        public DolarsiDolarValueDTOBuilder variacion(String variacion) {
            dolarsiDolarValueDTO.setVariacion(variacion);
            return this;
        }

        public DolarsiDolarValueDTOBuilder ventaCero(String ventaCero) {
            dolarsiDolarValueDTO.setVentaCero(ventaCero);
            return this;
        }

        public DolarsiDolarValueDTOBuilder decimales(String decimales) {
            dolarsiDolarValueDTO.setDecimales(decimales);
            return this;
        }

        public DolarsiDolarValueDTO build() {
            return dolarsiDolarValueDTO;
        }
    }
}