package com.github.filipe.desafioapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_news")
public class News extends BaseItem {

}
