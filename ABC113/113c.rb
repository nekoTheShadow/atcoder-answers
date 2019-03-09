n, m = gets.split.map(&:to_i)
prefectures, years, cities_hash = [], [], Hash.new{|hash, key| hash[key] = []}
(1..m).each do |city|
  prefecture, year = gets.split.map(&:to_i)
  
  prefectures[city] = prefecture
  years[city] = year
  cities_hash[prefecture] << city
end

sequences = []
cities_hash.each_value do |cities|
  cities.sort_by{|city| years[city]}.each.with_index(1){|city, sequence| sequences[city] = sequence}
end

(1..m).each do |city|
  printf("%06d%06d\n", prefectures[city], sequences[city])
end





