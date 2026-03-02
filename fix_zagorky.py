import json

filepath = "src/main/resources/assets/dayzreforge/models/item/food_zagorky.json"
with open(filepath, 'r') as f:
    data = f.read()

# Replace the texture id "0" with "texture" which is standard for Forge models
data = data.replace('"0":', '"texture":')
data = data.replace('"#0"', '"#texture"')

# Rename properties that cause issues in Forge 1.20 Java item models
data = data.replace('"format_version"', '"deleted_version"')
data = data.replace('"texture_size"', '"deleted_texture_size"')
data = data.replace('"groups"', '"deleted_groups"')

with open(filepath, 'w') as f:
    f.write(data)
