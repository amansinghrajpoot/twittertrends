package twittertrends;

public class Hashing {

	public static double[] hash(String tweet ) {
		
		String[] wordlist = null;
		String[] trigram = null;
		
		double[] vectors = null;
		
		int len = 591;
		vectors = new double[len];
		
		 int index = 0;
	     long h = 0;
			
			for(int i = 0; i < len; i ++) {
				vectors[i] = 0.0;
			}
			
			 wordlist = tweet.split(" ");
			 
			 if (wordlist.length > 2) {
			 trigram = new String[wordlist.length - 2];
			 
			        for (int l =0; l < wordlist.length -2; l ++) {
				    trigram[l] = wordlist[l]+wordlist[l+1]+wordlist[l+2];
			         }
			 }
			 else {
				 trigram = new String[wordlist.length];
				 for (int l =0; l < wordlist.length; l ++) {
					    trigram[l] = wordlist[l];
				         }
				 
			 }
			 
		    
		     for (int i =0; i < trigram.length; i ++) {
		    	 
		    	 h = Hashing.hash64(trigram[i]);
		    	 index = Math.floorMod(h, len);
		    	 vectors[index] = vectors[index] +1 ;
		    	 h = 0;
		    	 index = 0;
		    	 
		      }
			return vectors;
		     
		       
		     
     }
		
	
	
	private static long hash64( final String text) {
		final byte[] bytes = text.getBytes(); 
		return hash64( bytes, bytes.length);
	}
	
	private static long hash64( final byte[] data, int length) {
		return hash64( data, length, 0xe17a1465);
	}
	
	private static long hash64( final byte[] data, int length, int seed) {
		final long m = 0xc6a4a7935bd1e995L;
		final int r = 47;

		long h = (seed&0xffffffffl)^(length*m);

		int length8 = length/8;

		for (int i=0; i<length8; i++) {
			final int i8 = i*8;
			long k =  ((long)data[i8+0]&0xff)      +(((long)data[i8+1]&0xff)<<8)
					+(((long)data[i8+2]&0xff)<<16) +(((long)data[i8+3]&0xff)<<24)
					+(((long)data[i8+4]&0xff)<<32) +(((long)data[i8+5]&0xff)<<40)
					+(((long)data[i8+6]&0xff)<<48) +(((long)data[i8+7]&0xff)<<56);
			
			k *= m;
			k ^= k >>> r;
			k *= m;
			
			h ^= k;
			h *= m; 
		}
		
		switch (length%8) {
		case 7: h ^= (long)(data[(length&~7)+6]&0xff) << 48;
		case 6: h ^= (long)(data[(length&~7)+5]&0xff) << 40;
		case 5: h ^= (long)(data[(length&~7)+4]&0xff) << 32;
		case 4: h ^= (long)(data[(length&~7)+3]&0xff) << 24;
		case 3: h ^= (long)(data[(length&~7)+2]&0xff) << 16;
		case 2: h ^= (long)(data[(length&~7)+1]&0xff) << 8;
		case 1: h ^= (long)(data[length&~7]&0xff);
		        h *= m;
		};
	 
		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;

		return h;
	}
	
}
	
	

