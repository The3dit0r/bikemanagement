package com.december.bikemanager.data;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class Product {
  public String id;
  public String name;
  public String brandID;
  public String categoryID;
  public int modelYear;
  public double listPrice;

  public Product(String id, String name, String brandID, String categoryID, int modelYear, double listPrice) {
    this.id = id;
    this.name = name;
    this.brandID = brandID;
    this.categoryID = categoryID;
    this.modelYear = modelYear;
    this.listPrice = listPrice;
  }

  public Product clone() {
    return new Product(id, name, brandID, categoryID, modelYear, listPrice);
  }

  public void setId(String id) throws Exception {
    if (Utility.isnt(id)) {
      throw new Exception("str-len-zero");
    }

    this.id = id;
  }

  public void setName(String name) throws Exception {
    if (Utility.isnt(name)) {
      throw new Exception("str-len-zero");
    }

    this.name = name;
  }

  public void setBrandID(String brandID) throws Exception {
    if (Utility.isnt(brandID)) {
      throw new Exception("str-len-zero");
    }

    this.brandID = brandID;
  }

  public void setCategoryID(String categoryID) throws Exception {
    if (Utility.isnt(categoryID)) {
      throw new Exception("str-len-zero");
    }

    this.categoryID = categoryID;
  }

  public void setModelYear(int modelYear) throws Exception {
    if (modelYear < 0) {
      throw new Exception("not-pos-int");
    }

    this.modelYear = modelYear;
  }

  public void setModelYear(String modelYear) throws Exception {
    try {
      int year = Integer.parseInt(modelYear);
      setModelYear(year);
    } catch (Exception e) {
      throw new Exception("not-int");
    }
  }

  public void setListPrice(double listPrice) throws Exception {
    if (listPrice < 0) {
      throw new Exception("not-pos");
    }
    this.listPrice = listPrice;
  }

  public void setListPrice(String listPrice) throws Exception {
    if (!Utility.isDouble(listPrice)) {
      throw new Exception("not-double");
    }

    setListPrice(Double.parseDouble(listPrice));
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBrandID() {
    return brandID;
  }

  public String getCategoryID() {
    return categoryID;
  }

  public int getModelYear() {
    return modelYear;
  }

  public double getListPrice() {
    return listPrice;
  }

  @Override
  public String toString() {
    return String.format("%s, %s, %s, %s, %d, %.2f", id,
        name,
        brandID,
        categoryID,
        modelYear,
        listPrice);
  }

  public String get(String type) {
    switch (type) {
      case "name":
        return Utility.is(name) ? name : "NAME";

      case "brand":
        return Utility.is(brandID) ? brandID : "BRAND";

      case "category":
        return Utility.is(categoryID) ? categoryID : "CATEGORY";

      case "year":
        return modelYear >= 0 ? modelYear + "" : "YEAR";

      case "price":
        return listPrice >= 0 ? listPrice + "$" : "PRICE";

      default:
      case "id":
        return Utility.is(id) ? id : "ID";
    }
  }

  public boolean match(String type, String query) {
    String q = query.toLowerCase();

    switch (type) {
      case "id": {
        return id.toLowerCase().contains(q);
      }

      case "brand": {
        return brandID.toLowerCase().contains(q);
      }

      case "category": {
        return categoryID.toLowerCase().contains(q);
      }

      case "name": {
        return name.toLowerCase().contains(q);
      }

      default: {
        return toString().toLowerCase().contains(q);
      }
    }
  }
}
