package com.nephew.common.model.generic.entities.text;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public class Text {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private TextType type = TextType.PARAGRAPH;

	@Transient
	private TextType[] typeArr = TextType.values();

	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	private TextSize size = TextSize._base;

	@Transient
	private TextSize[] sizeArr = TextSize.values();

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private TextAlign align = TextAlign.left;

	@Transient
	private TextAlign[] alignArr = TextAlign.values();

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private TextWeight weight = TextWeight.normal;

	@Transient
	private TextWeight[] weightArr = TextWeight.values();
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private Float floatVal = Float.float_none;
	
	@Transient
	private Float[] floatArr = Float.values();

	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	private Width width = Width.none;

	@Transient
	private Width[] widthArr = Width.values();

	@Enumerated(EnumType.STRING)
	@Column(length = 5, nullable = false)
	private Height height = Height.none;

	@Transient
	private Height[] heightArr = Height.values();

	private int position;

	@Column(columnDefinition = "text")
	private String text;
	
	@Column(columnDefinition = "text")
	private String imageUrl;

	private boolean italic = false;

	@Override
	public int hashCode() {
		return Objects.hash(align, position, size, text, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Text other = (Text) obj;
		return align == other.align && position == other.position && size == other.size
				&& Objects.equals(text, other.text) && type == other.type;
	}

	public Float getFloatVal() {
		return floatVal;
	}

	public void setFloatVal(Float floatVal) {
		this.floatVal = floatVal;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TextType getType() {
		return type;
	}

	public void setType(TextType type) {
		this.type = type;
	}

	public TextType[] getTypeArr() {
		return typeArr;
	}

	public void setTypeArr(TextType[] typeArr) {
		this.typeArr = typeArr;
	}

	public TextSize getSize() {
		return size;
	}

	public void setSize(TextSize size) {
		this.size = size;
	}

	public TextSize[] getSizeArr() {
		return sizeArr;
	}

	public void setSizeArr(TextSize[] sizeArr) {
		this.sizeArr = sizeArr;
	}

	public TextAlign getAlign() {
		return align;
	}

	public void setAlign(TextAlign align) {
		this.align = align;
	}

	public TextAlign[] getAlignArr() {
		return alignArr;
	}

	public void setAlignArr(TextAlign[] alignArr) {
		this.alignArr = alignArr;
	}

	public TextWeight getWeight() {
		return weight;
	}

	public void setWeight(TextWeight weight) {
		this.weight = weight;
	}

	public TextWeight[] getWeightArr() {
		return weightArr;
	}

	public void setWeightArr(TextWeight[] weightArr) {
		this.weightArr = weightArr;
	}

	public Float[] getFloatArr() {
		return floatArr;
	}

	public void setFloatArr(Float[] floatArr) {
		this.floatArr = floatArr;
	}

	public Width getWidth() {
		return width;
	}

	public void setWidth(Width width) {
		this.width = width;
	}

	public Width[] getWidthArr() {
		return widthArr;
	}

	public void setWidthArr(Width[] widthArr) {
		this.widthArr = widthArr;
	}

	public Height getHeight() {
		return height;
	}

	public void setHeight(Height height) {
		this.height = height;
	}

	public Height[] getHeightArr() {
		return heightArr;
	}

	public void setHeightArr(Height[] heightArr) {
		this.heightArr = heightArr;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	@Override
	public String toString() {
		return "Text [type=" + type + ", size=" + size + ", align=" + align + ", position=" + position + ", text="
				+ text + "]";
	}

}
