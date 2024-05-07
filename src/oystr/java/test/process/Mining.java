package oystr.java.test.process;

public class Mining {
	private static int getPosTag(String sText, String sTag) {
		int iPos;
		iPos = sText.indexOf(sTag);
		return iPos<0?iPos:iPos + sTag.length();
	}
	public class Agrofy {		
		private static String getProductDetailTag(String sHTML, String sTag) {
			int iPos,iAux;		
			String sAux = "";
			iPos = getPosTag(sHTML,"productDetails-infoSection");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),sTag);
				if(iAux>0) {
					iPos = iPos + iAux;
					iAux = getPosTag(sHTML.substring(iPos),"</li>")-5;
					if(iAux>0) {
						while(sHTML.charAt(iPos + iAux)!='>'&&iAux>=0) {
							iAux--;
						}
						iPos = iPos + iAux + 1;
						iAux = getPosTag(sHTML.substring(iPos),"</li>")-5;
						if(iAux>0) {
							sAux = sHTML.substring(iPos, iPos + iAux);
						}
					}
				}
			}		
			return sAux;
		}
		public static String getPictureURL(String sHTML) {		
			int iPos,iAux;
			String sStop = "\"";
			String sAux = "";
			iPos = getPosTag(sHTML,"ProductCarousel");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),"<img src=\"");
				if(iAux>0) {
					iPos = iPos + iAux;
					iAux = getPosTag(sHTML.substring(iPos),sStop)-sStop.length();
					if(iAux>0) {
						sAux = sHTML.substring(iPos, iPos + iAux);
					}
				}
			}
			return sAux;
		}
		public static String getPrice(String sHTML) {
			int iPos,iAux;
			String sStop = "</span>";
			String sAux = "";
			iPos = getPosTag(sHTML,"prices-totalPrice\">");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),sStop)-sStop.length();
				if(iAux>0) {
					sAux = sHTML.substring(iPos, iPos + iAux);
					sAux = sAux.replaceAll("<","").replace("!","").replace("-","").replace(">","").replace("Â","").replace(" ","");
				}
			}
			return sAux;
		}
		public static String getTag(String sHTML, String sTag) {		
			return getProductDetailTag(sHTML, sTag);
		}
	}
	public class TratoresColheitadeiras {
		public static String getPictureURL(String sHTML) {		
			int iPos,iAux;
			String sStop = "?";
			String sAux = "";
			iPos = getPosTag(sHTML,"zoompro-span");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),"src=\"");
				if(iAux>0) {
					iPos = iPos + iAux;
					iAux = getPosTag(sHTML.substring(iPos),sStop)-sStop.length();
					if(iAux>0) {
						sAux = sHTML.substring(iPos, iPos + iAux);
					}
				}
			}
			return sAux;
		}
		public static String getLocalizacao(String sHTML) {
			int iPos,iAux;
			String sAux = "";
			iPos = getPosTag(sHTML,"imgLogo");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),"<li>");
				if(iAux>0) {
					iPos = iPos + iAux;
					iAux = getPosTag(sHTML.substring(iPos),"</li>")-5;
					if(iAux>0) {
						sAux = sHTML.substring(iPos, iPos + iAux);
						sAux = limpeza(sAux);
					}
				}
			}
			return sAux;
		}
		private static String getProductDetailTag(String sHTML, String sTag) {
			int iPos,iAux;		
			String sAux = "";
			iPos = getPosTag(sHTML,"product-description");
			if(iPos>0) {
				iAux = getPosTag(sHTML.substring(iPos),sTag);
				if(iAux>0) {
					iPos+=iAux;
					iAux = getPosTag(sHTML.substring(iPos),"<strong>");
					if(iAux>0) {
						iPos+=iAux;
						iAux = getPosTag(sHTML.substring(iPos),"</strong>")-9;						
						if(iAux>0) {
							sAux = sHTML.substring(iPos, iPos + iAux);
						}						
					}
				}
			}		
			return limpeza(sAux);
		}
		private static String limpeza(String sString) {
			return sString.replace("\"", "").replace("<br>"," - ").replace("\r\n", "").replace("  ","").trim();
		}
		public static String getTag(String sHTML, String sTag) {		
			return getProductDetailTag(sHTML, sTag);
		}
	}
	public class MercMaq {		
		private static String getProductDetailTag(String sHTML, String sTag) {
			int iPos,iAux;		
			String sAux = "";
			iPos = getPosTag(sHTML,"\"@type\":\"Product\"");
			if(iPos>0) {
				switch(sTag) {
					case "image":
						iAux = getPosTag(sHTML.substring(iPos),"\"" + sTag + "\":[\"");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),"\"")-1;						
							if(iAux>0) {
								sAux = "https:" + sHTML.substring(iPos, iPos + iAux);
							}							
						}
						break;
					case "brand":
						iAux = getPosTag(sHTML.substring(iPos),"\"" + sTag + "\":");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),"\"name\":\"");
							if(iAux>0) {
								iPos+=iAux;
								iAux = getPosTag(sHTML.substring(iPos),"\"")-1;						
								if(iAux>0) {
									sAux = sHTML.substring(iPos, iPos + iAux);
								}
							}		
						}
						break;
					case "model":
						iAux = getPosTag(sHTML.substring(iPos),"\"" + sTag + "\":\"");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),"\"")-1;						
							if(iAux>0) {
								sAux = sHTML.substring(iPos, iPos + iAux);
							}							
						}
						break;
					case "Ano Fabricação":
						iAux = getPosTag(sHTML.substring(iPos), sTag + ":");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),",")-1;						
							if(iAux>0) {
								sAux = sHTML.substring(iPos, iPos + iAux).trim();
							}	
						}
						break;
					case "City":
						iAux = getPosTag(sHTML.substring(iPos), "\"description\":\"");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos), "-");
							if(iAux>0) {
								iPos+=iAux;
								iAux = getPosTag(sHTML.substring(iPos), "-");
								if(iAux>0) {
									iPos+=iAux;
									iAux = getPosTag(sHTML.substring(iPos), "-")-1;
									if(iAux>0) {
										sAux = sHTML.substring(iPos, iPos + iAux).trim();
									}
								}
							}
						}
						break;
					case "price":
						iAux = getPosTag(sHTML.substring(iPos), "\"" + sTag + "\":\"");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),"\"")-1;						
							if(iAux>0) {
								sAux = sHTML.substring(iPos, iPos + iAux).replace(".",",");
							}
						}
						break;
					case "Horas trabalhadas":
						iAux = getPosTag(sHTML.substring(iPos), sTag + ":");
						if(iAux>0) {
							iPos+=iAux;
							iAux = getPosTag(sHTML.substring(iPos),"\"")-1;						
							if(iAux>0) {
								sAux = sHTML.substring(iPos, iPos + iAux).trim();
							}
						}
						break;
				}
			}
			return sAux;
		}
		public static String getTag(String sHTML, String sTag) {		
			return getProductDetailTag(sHTML, sTag);
		}		
	}
}
