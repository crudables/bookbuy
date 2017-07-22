package com.ables.bookbuy.models;

import java.time.LocalDateTime;

import javax.persistence.*;

@MappedSuperclass
public class AbstractDomainClass implements DomainObject{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public Long id;
 
    @Version
    private Integer version;
 
    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;
 
    @Override
    public Long getId() {
        return this.id;
    }
 
    
    public Integer getVersion() {
        return version;
    }
 
    public void setVersion(Integer version) {
        this.version = version;
    }
 
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
 
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
 
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = LocalDateTime.now();
        if (dateCreated==null) {
            dateCreated = LocalDateTime.now();
        }
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractDomainClass other = (AbstractDomainClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
