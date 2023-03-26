package efrei.projectCloud.warehouseMicroservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "`warehouse`")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String location;

    @JsonManagedReference
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.REMOVE)
    private List<WarehouseItem> warehouseItems;

    public Warehouse() {}

    public Warehouse(Long id, String name, String location, List<WarehouseItem> warehouseItems) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.warehouseItems = warehouseItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<WarehouseItem> getWarehouseItems() {
        return warehouseItems;
    }

    public void setWarehouseItems(List<WarehouseItem> warehouseItems) {
        this.warehouseItems = warehouseItems;
    }
}

